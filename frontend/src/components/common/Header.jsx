import React from 'react'

const HeaderMain = (title) => {
  return (
    <header>
      <div className='overlay'></div>
      <div className='container'>
        <h1 className='header-title text-center'>{title}</h1>
      </div>
    </header>
  )
} 

export default HeaderMain
