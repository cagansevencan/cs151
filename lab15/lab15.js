'use strict'

const n1 = { x: 5, y: 5, color: 'white', size: 5, type: 'circle' }
const n2 = { x: 10, y: 10, color: 'black', size: 5, type: 'circle' }

const e = { start: n1, end: n2 }

const graph =
{
  nodes: [n1, n2], edges: [e], type: 'simple' }

function findNode (g, x, y) {
  for (let i = g.nodes.length - 1; i >= 0; i--) {
    const n = g.nodes[i]
    if (n.x === x && n.y === y) return n
  }
  return undefined
}
console.log(findNode(graph, 10, 100))
