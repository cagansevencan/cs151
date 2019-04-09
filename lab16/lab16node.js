'use strict'

function createCircleNode(x,y,size,color)
{
return {
  getBounds: () => {
    return{
      x: x,
      y: y,
      width: size,
      height: size
      }
    }
    contains: p => {
  return
    { x: ..., y: ..., width: ..., height: ... }.
  }
  }
}

module.exports = {createCircleNode()
