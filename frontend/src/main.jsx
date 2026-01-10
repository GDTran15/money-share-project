import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import { createBrowserRouter , RouterProvider } from 'react-router-dom'

import RegisterPage from './page/RegisterPage'
import LoginPage from './page/LoginPage';
import HomePage from './page/HomePage';



const router = createBrowserRouter([
  { path:"/register", element: <RegisterPage/>},
  { path:"/", element: <LoginPage/>},
  { path:"/home", element: <HomePage/>}
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>
)