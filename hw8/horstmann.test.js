'use strict'

/* eslint-env jest */

const { createHashSet } = require('./hashset')

test('simple test', () => {
  const set = createHashSet(10, x => x, (x, y) => x === y)
  set.add(1)
  set.add(7)
  set.add(2)
  set.add(9)

  const iter = set.iterator()
  expect(iter.next()).toBe(1)
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(7)
  expect(iter.next()).toBe(9)
  expect(iter.hasNext()).toBe(false)
})

test('pattern A', () => {
  const set = createHashSet(5, x => x, (x, y) => x === y)
  set.add(0)
  set.add(1)
  set.add(2)
  set.add(3)
  set.add(4)
  let iter = set.iterator()
  expect(iter.next()).toBe(0)
  expect(iter.next()).toBe(1)
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(3)
  expect(iter.next()).toBe(4)
  expect(iter.hasNext()).toBe(false)
  iter = set.iterator()
  iter.next()
  iter.remove()
  iter = set.iterator()
  expect(iter.next()).toBe(1)
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(3)
  expect(iter.next()).toBe(4)
  expect(iter.hasNext()).toBe(false)
})

test('pattern B', () => {
  const set = createHashSet(5, x => x, (x, y) => x === y)
  set.add(0)
  set.add(2)
  set.add(4)
  let iter = set.iterator()
  expect(iter.next()).toBe(0)
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(4)
  expect(iter.hasNext()).toBe(false)
  iter = set.iterator()
  iter.next()
  iter.remove()
  iter = set.iterator()
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(4)
  expect(iter.hasNext()).toBe(false)
})

test('pattern C', () => {
  const set = createHashSet(5, x => x, (x, y) => x === y)
  set.add(0)
  set.add(5)
  set.add(1)
  set.add(6)
  set.add(11)
  let iter = set.iterator()
  expect(iter.next()).toBe(5)
  expect(iter.next()).toBe(0)
  expect(iter.next()).toBe(11)
  expect(iter.next()).toBe(6)
  expect(iter.next()).toBe(1)
  expect(iter.hasNext()).toBe(false)
  iter = set.iterator()
  iter.next()
  iter.remove()
  iter = set.iterator()
  expect(iter.next()).toBe(0)
  expect(iter.next()).toBe(11)
  expect(iter.next()).toBe(6)
  expect(iter.next()).toBe(1)
  expect(iter.hasNext()).toBe(false)
})

test('another hash function', () => {
  function stringHash (s) {
    let h = 0
    for (const c of s) {
      h = 31 * h + c.charCodeAt()
    }
    return h
  }
  const set = createHashSet(10, stringHash, (x, y) => x === y)
  set.add('Hello') // 69609650
  set.add('World') // 83766130
  set.add('Goodbye') // 66296185073
  const iter = set.iterator()
  expect(iter.next()).toBe('World')
  expect(iter.next()).toBe('Hello')
  expect(iter.next()).toBe('Goodbye')
  expect(iter.hasNext()).toBe(false)
})

test('another equals function', () => {
  const set = createHashSet(10, x => 0, (x, y) => x.toLowerCase() === y.toLowerCase())
  set.add('Hello')
  set.add('World')
  expect(set.contains('hello')).toBe(true)
  expect(set.contains('goodbye')).toBe(false)
})

test('no such element', () => {
  const set = createHashSet(10, x => x, (x, y) => x === y)
  set.add(1)
  set.add(7)
  set.add(2)
  set.add(9)

  const iter = set.iterator()
  expect(iter.next()).toBe(1)
  expect(iter.next()).toBe(2)
  expect(iter.next()).toBe(7)
  expect(iter.next()).toBe(9)
  expect(() => iter.next()).toThrow()
})

test('remove without next', () => {
  const set = createHashSet(10, x => x, (x, y) => x === y)
  set.add(1)
  set.add(7)
  set.add(2)
  set.add(9)

  const iter = set.iterator()
  expect(() => iter.remove()).toThrow()
})

test('double remove', () => {
  const set = createHashSet(10, x => x, (x, y) => x === y)
  set.add(1)
  set.add(7)
  set.add(2)
  set.add(9)

  const iter = set.iterator()
  iter.next()
  iter.remove()
  expect(() => iter.remove()).toThrow()
})
