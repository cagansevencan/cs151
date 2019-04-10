'use strict'

function createCircleNode (x, y, size, color) {
  return {
    getBounds: () => {
      return {
        x: x,
        y: y,
        width: size,
        height: size
      }
    },
    contains: p => {
      return (x + size / 2 - p.x) ** 2 + (y + size / 2 - p.y) ** 2 <= size ** 2 / 4
    },
    translate: (dx, dy) => {
      x += dx
      y += dy
    },
    draw: () => {
      const panel = document.getElementById('graphpanel')
      const circle = document.createElementNS('http://www.w3.org/2000/svg', 'circle')
      circle.setAttribute('cx', x + size / 2)
      circle.setAttribute('cy', y + size / 2)
      circle.setAttribute('r', size / 2)
      circle.setAttribute('fill', color)
      panel.appendChild(circle)
    }
  }
}

function drawGrabber(x, y) {
  const size = 5;
      const panel = document.getElementById('graphpanel')
      const rect = document.createElementNS('http://www.w3.org/2000/svg', 'rect')
      rect.setAttribute('x', x - size / 2)
      rect.setAttribute('y', y - size / 2)
      rect.setAttribute('width', size )
      rect.setAttribute('height', size )
      rect.setAttribute('fill', 'black')
      panel.appendChild(rect)

}

class Graph {
  constructor() {
    this.nodes = []
    this.edges = []
  }
  add(n) {
    this.nodes.push(n)
  }
  findNode(p) {
    for (let i = this.nodes.length - 1; i >= 0; i--) {
      const n = this.nodes[i]
      if (n.contains(p)) return n
    }
    return undefined
  }
  draw() {
    for (const n of this.nodes) {
      n.draw()
    }
  }
}

document.addEventListener('DOMContentLoaded', function () {
  const graph = new Graph()
  const n1 = createCircleNode(10, 10, 20, 'goldenrod')
  const n2 = createCircleNode(30, 30, 20, 'blue')
  graph.add(n1)
  graph.add(n2)
  graph.draw()

  function mouseLocation(event) {
    var rect = panel.getBoundingClientRect();
    return {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top,
    }
  }

  let selected = undefined

  function repaint() {
    panel.innerHTML = ''
    graph.draw()
    if (selected !== undefined) {
      const bounds = selected.getBounds()
      drawGrabber(bounds.x, bounds.y)
      drawGrabber(bounds.x + bounds.width, bounds.y)
      drawGrabber(bounds.x, bounds.y + bounds.height)
      drawGrabber(bounds.x + bounds.width, bounds.y + bounds.height)
    }
  }

  let dragStartPoint = undefined
  let dragStartBounds = undefined
  const panel = document.getElementById('graphpanel')
  panel.addEventListener('mousedown', event => {
    let mousePoint = mouseLocation(event)
    dragStartPoint = mousePoint
    selected = graph.findNode(mousePoint)
    if (selected){
       dragStartBounds = selected.getBounds()}
    repaint()
  })

  panel.addEventListener('mousemove', event => {
    let mousePoint = mouseLocation(event)
    if(dragStartBounds !== null) {
      //dragging
      const bounds = selected.getBounds()
      selected.translate(
        dragStartBounds.x - bounds.x
        + mousePoint.x - dragStartPoint.x,
        dragStartBounds.y - bounds.y
        + mousePoint.y - dragStartPoint.y)
    }

    repaint()
  })

  panel.addEventListener('mouseup', event => {
    let mousePoint = mouseLocation(event)
    dragStartPoint = undefined
    dragStartBounds = undefined
    repaint()
  })
})
