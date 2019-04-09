'use strict'

const {createCircleNode} = required ('./node')

test('...', () =>){
  const n = createCircleNode(10,10,20, 'pink')
  const bounds = n.getBounds()
  expect(bounds.width).toBe(20)
}
