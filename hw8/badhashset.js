'use strict'

function createHashSet (bucketsLength, hashCode, equals) {
  let size = 0
  let buckets = []
  for (let i = 0; i < bucketsLength; i++) buckets.push(null)

  return {
    contains: x => {
      let h = hashCode(x)
      if (h < 0) h = -h
      h = h % buckets.length
      let current = buckets[h]
      while (current !== null) {
        if (equals(current.data, x)) {
          return true
        }
        current = current.next
      }
      return false
    },
    add: x => {
      let h = hashCode(x)
      if (h < 0) h = -h
      h = h % buckets.length
      let current = buckets[h]
      while (current !== null) {
        if (equals(current.data, x)) {
          return false // Already in the set
        }
        current = current.next
      }
      const newLink = { data: x, next: buckets[h] }
      buckets[h] = newLink
      size++
      return true
    },
    remove: x => {
      let h = hashCode(x)
      if (h < 0) h = -h
      h = h % buckets.length
      let current = buckets[h]
      let previous = null
      while (current !== null) {
        if (equals(current.data, x)) {
          if (previous === null) {
            buckets[h] = current.next
          } else {
            previous.next = current.next
          }
          size--
          return true
        }
        previous = current
        current = current.next
      }
      return false
    },
    iterator: () => {
      let bucket = 0
      let current = null
      let previousBucket = buckets.length
      let previous = null
      // set bucket to the index of the first nonempty bucket
      while (bucket < buckets.length && buckets[bucket] === null) {
        bucket++
      }
      if (bucket < buckets.length) {
        current = buckets[bucket]
      }

      return {
        hasNext: () => current !== null,
        next: () => {
          let r = current.data

          if (current.next === null) { // move to next bucket
            previousBucket = bucket
            bucket++

            while (bucket < buckets.length && buckets[bucket] === null) {
              bucket++
            }
            if (bucket < buckets.length) {
              current = buckets[bucket]
            } else {
              current = null
            }
          } else { // move to next element in bucket
            previous = current
            current = current.next
          }

          return r
        },
        remove: () => {
          if (previous !== null) {
            previous.next = previous.next.next
          } else if (previousBucket < buckets.length) {
            buckets[previousBucket] = null
          } else {
            throw Error('remove: Illegal state')
          }
          previous = null
          previousBucket = buckets.length
        }
      }
    },
    size: () => size
  }
}

module.exports = { createHashSet }
