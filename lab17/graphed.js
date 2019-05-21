'use strict'

function createCircleNode (x, y, size, color) {
let children = []

children.push(createCircleNode(x,y,size,color))
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
    const p = { x: n.getBounds().x, y: n.getBounds().y }

      Boolean accepted = false
      Boolean insideANode = false
      for (int i = nodes.size() - 1; i >= 0 && !accepted; i--)
      {
         Node parent = (Node)nodes.get(i);
         if (parent.contains(p))
         {
            insideANode = true;
            if (parent.addNode(n, p)) accepted = true;
         }
      }
      if (insideANode && !accepted)
         return false;
      nodes.add(n);
      return true;
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
    for (const w of this.edges) {
      e.draw()
    }
  }
connect(e, p1, p2)
{
  const n1 = this.findNode(p1)
  const n2 = this.findNode(p2)
  if (n1 !== undefined && n2 !== undefined) {
    e.connect(n1, n2)
    this.edges.push(e)
    return true
  }
  return false
}
}

function center(rect){
  return { x: rect.x + rect.width / 2, y: rect.y + rect.height / 2 }
}

function createLineEdge(){
  let start = undefined
  let end = undefined
  return{
    connect: (s, e) => {
      start = s
      end = e
    },
    draw: () => {
      const panel = document.getElementById('graphpanel')
      const line = document.createElementNS('http://www.w3.org/2000/svg', 'line')
      let p1 = center(start.getBounds())
      let p2 = center(end.getBounds())

    }
  }
}


document.addEventListener('DOMContentLoaded', function () {
  const graph = new Graph()

  const n0 = createCircleNode(-5, -5, 70, 'pink')
  const n1 = createCircleNode(30, 30, 20, 'blue')
  graph.add(n0)
  graph.add(n1)
  graph.draw()
  const e = createLineEdge()
  graph.connect(e, { x: 20, y: 20 }, { x: 40, y: 40 })

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
