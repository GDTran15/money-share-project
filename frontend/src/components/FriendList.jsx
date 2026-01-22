import axios from "axios"
import { useEffect, useState } from "react";
import { Card, Col,Container,Row } from 'react-bootstrap';



export default function FriendList(){
    const token = localStorage.getItem("token");
    const [friendLists,setFriendList] = useState([]);

    const getFriendList = async () => {
        try {
            const response = await axios.get("http://localhost:8080/friends",{
                headers:{
                      "Authorization": `Bearer ${token}`
                }
            })
            setFriendList(response.data)
            console.log(response.data)
        } catch (error) {
            console.log(error)
        }
    }

     useEffect(() => {
        if (token) {
            getFriendList();
        }
    }, [token]);

    return(<>
        <Container className="mt-3">
            <Row>
                {friendLists.map(friend => (
                    <Col xs={4}>
                    <Card key={friend.userId} className="p-2">
                        <Card.Title>{friend.username}</Card.Title>
                        
                    </Card>
                    </Col>
                    ))}
            </Row>
        </Container>
    
    </>)
}