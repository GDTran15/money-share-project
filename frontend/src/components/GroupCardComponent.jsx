    import { Card ,Badge } from 'react-bootstrap';


export default function GroupCardComponent({title,memberList}){

    const currentUser = localStorage.getItem("username")
    return(<>
        <Card>
            <Card.Header>{title}</Card.Header>
            <Card.Body>
                <div className='d-flex flex-column'>
                    {memberList.map((m) => ( 
                     <span>
                            {m}
                            {currentUser === m ? <Badge pill bg='secondary' className='ms-2'>You</Badge> : ""}
                            
                    </span>
                    
                    )
                        
                    )}
                </div>
            </Card.Body>
        </Card>
    </>)
}