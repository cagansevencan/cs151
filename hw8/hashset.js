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
      let bucket = -1
      let current = null
      let previousBucket = -1
      let previous = null

      return {
        hasNext: () => {
          if (current !== null && current.next !== null) {
            return true
          }
          for (let b = bucket + 1; b < buckets.length; b++) {
            if (buckets[b] !== null) {
              return true
            }
          }
          return false
        },
        next: () => {
          previous = current

          previousBucket = bucket
          if (current === null || current.next === null) {
            // move to next bucket
            bucket++

            while (bucket < buckets.length && buckets[bucket] === null) {
              bucket++
            }
            if (bucket < buckets.length) {
              current = buckets[bucket]
            } else {
              throw Error('next: No such element')
            }
          } else { // move to next element in bucket
            current = current.next
          }
          return current.data
        },
        remove: () => {
          if (previous !== null && previous.next === current) {
            previous.next = current.next
          } else if (previousBucket < bucket) {
            buckets[bucket] = current.next
          } else {
            throw Error('next: Illegal state')
          }
          current = previous
          bucket = previousBucket
        }
      }
    },
    size: () => size
  }
}

module.exports = { createHashSet }
