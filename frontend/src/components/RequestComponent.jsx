import axios from 'axios';
import { Nav,Container,Row,Button,Col } from 'react-bootstrap';

export default function RequestComponent({username,friendRequestId,handleUserResponse}){
    const token = localStorage.getItem("token");

    const responseFriendRequest = async (status) => {
        try{
             await axios.put(`http://localhost:8080/friend-request/${friendRequestId}`,
            null,
            {
                params : {friendStatus: status},
                headers:{
                      "Authorization": `Bearer ${token}`
                }
            }
        )

        handleUserResponse();
        } catch(error) {
            console.log(error)
        }
    } 
    return(
        <>
            
                <Col lg="12" className='d-flex justify-content-between p-2 border-1 border-black'>
                    <div>
                        <h3>{username}</h3>
                    </div>
                    <div className=''>
                        <Button size='sm' variant='success' onClick={() => responseFriendRequest("ACCEPTED")}>Accept</Button>
                        <Button size='sm'variant='danger'onClick={() => responseFriendRequest("DECLINED")}>Decline</Button>
                    </div>
                </Col>
                           
        </>
    )
}