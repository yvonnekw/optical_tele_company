import React from 'react'
import { Card, CardBody } from 'react-bootstrap'

function CallCard({call}) {
  return (
      <col key={call.id} className='mb-4' xs={12}>
        <Card>
              <Card.Body className='d-flex flex-wrap align-items-center'>
                  <div className='flex-shrink-0 mr-3 mb-3 mb-0'>  
                      <Card.Img>
                          
                      </Card.Img
                      >
                  </div>
                  <div className='flex-grow-1 ml-3 px-5'>
                    <Card.Title className='call-color'>{call.totalTime}</Card.Title>
                    <Card.Title className='call-color'>{call.callCost}</Card.Title>  
                  </div>

                  
              </Card.Body>   
        </Card>
    </col>
  )
}

export default CallCard
