import { useState } from "react";
import FormComponent from "../components/FormComponent";
import InputComponent from "../components/InputComponent";
import axios from "axios";


export default function RegisterPage(){
    const [username,setUsername] =  useState("");
    const [password,setPassword] =  useState("");
   const [email,setEmail] = useState("");
   const [error,setError] = useState("");
   const [validError,setValidError] = useState({});

   const handleSubmit = async (e) =>{
    e.preventDefault(); 
    setError("");
    setValidError({});
    try{
        const res = await axios.post("http://localhost:8080/register",{
             
                username: username,
                password: password,
                email: email,   
              
        });
      setUsername(""); setPassword(""); setEmail(""); 
     console.log(res);
       
    } catch (error){
        console.log(error)
       if (error.response.data.message) {
        console.log(error.response.data);
        setError(error.response.data);
    } else {
       setValidError(error.response.data);
       console.log(error.response.data.username)
    }
    } 
   }
 
    return( 
        <>
            <FormComponent title="Register" subTitle="Join splitter community"
       optional="Already have an account? Sign in"
       linkTo="/">
        <form onSubmit={handleSubmit}>
        <InputComponent
        labelText="Username"
        inputType="text"
        changeHandle={(e) => setUsername(e.target.value)}
        inputValue={username}
        placeholderValue="trangiaiduong"
        validationError={validError.username}
        >
         </InputComponent>
        <InputComponent
        labelText="Password"
        inputType="password"
        inputValue={password}
        changeHandle={(e) => setPassword(e.target.value)}
        validationError={validError.password}

        >
         </InputComponent>
       
        <InputComponent
        labelText="Email"
        inputType="email"
        placeholderValue="d@gmail.com"
        inputValue={email}
        changeHandle={(e) => setEmail(e.target.value)}
        validationError={validError.email}

        >
         </InputComponent>
                     <button type="submit" className="btn btn-warning w-100 mt-2">Register</button>
         <p className="text-danger mt-3">{error !== "" ?  `*${error.message}` : ""}</p>
        </form>
       </FormComponent>
           
        </>
    )
}