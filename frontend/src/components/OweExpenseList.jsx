import axios from "axios"
import { useEffect, useState } from "react";
import { Card, Col,Container,Row } from 'react-bootstrap';



export default function OweExpenseList(){
    const token = localStorage.getItem("token");
    const [oweExpense,setOweExpense] = useState([]);

     const getOweExpense = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/expense`, {
                headers: {
                     "Authorization": `Bearer ${token}`
                
                }
            });
            setOweExpense(response.data)
        } catch (error) {
            console.log(error);
        }
    }
    

    

     useEffect(() => {
        if (token) {
            getOweExpense();
        }
    }, [token]);

    return(<>
        <Container className="mt-3">
            <Row>
                {oweExpense.map(expense => (
                    <Col xs={12}>
                    <Card  className="p-2">
                        <Card.Title>{expense.expenseName}</Card.Title>
                        <Card.Subtitle>Added by you - {expense.date}</Card.Subtitle> {/*fix thiscolor later*/}
                        
                    </Card>
                    </Col>
                    ))}
            </Row>
        </Container>
    
    </>)
}