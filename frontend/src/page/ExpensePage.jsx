import { useState,useEffect } from 'react';
import { Col,Container,Row,Button,Modal,Form, ModalBody,Badge } from 'react-bootstrap';
import SearchBar from '../components/SearchBar';
import axios from 'axios';
import GroupCardComponent from '../components/GroupCardComponent';
import OweExpenseList from '../components/OweExpenseList';


export default function ExpensePage(){
    const [showCreateExpense,setShowCreateExpense] = useState(false);
    const [shareTarget,setShareTarget] = useState("FRIEND")
    const [getTargetResult,setGetTargetResult] = useState([])
    const [startSearch,setStartSearch] = useState(false);
    const [search,setSearch] = useState("");
    const token = localStorage.getItem("token");
    const [error,setError] = useState({});
    const [targetId,setTargetId] = useState();
    const [expenseName,setExpenseName] = useState("");
    const [amount,setAmount] = useState(0);
    const [date,setDate] = useState(null);
    const [expenseType,setExpenseType] = useState(null);

 
    
    

    const endpoint = `${shareTarget.toLowerCase()}s`;
    const getTargetOption = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/${endpoint}/search`, {
                  params :{
                  page : 0,
                  size : 5,
                  search : search
                },
                headers:{
                      "Authorization": `Bearer ${token}`
                }
        });
        console.log(response);
        setGetTargetResult(response.data.content);
        } catch (error) {
            console.log(error);
        }
    }

   

    useEffect(() => {
        if (search.trim() !== "" && startSearch) {
     getTargetOption();
  }
}       , [shareTarget, search]);

    useEffect(() => {
        setGetTargetResult([])
    },[shareTarget])

    const addExpense = async (e) => {
        e.preventDefault();
    try {
        const response = await axios.post(
        "http://localhost:8080/expense",
        {
            expenseName: expenseName,
            amount: amount,
            date: date,
            expenseType: expenseType,
            expenseShareTo: shareTarget,
            targetId: targetId
        },
        {
            headers: {
            Authorization: `Bearer ${token}`
            }
        }
        );
        
         setExpenseName("");
        setAmount(0);
        setDate(null);
        setExpenseType("");
        setShareTarget("FRIEND");

        setSearch("");
        setTargetId(undefined);
        setGetTargetResult([]);
        setStartSearch(false);
        setError({})
        
        console.log(response.data);
    } catch (err) {
        if (err.response && err.response.data) {
            console.log(err.response.data)
      setError(err.response.data); 
    } else {
      console.log(err);
    }
    }
    };

    return (
        <>
        <header>
          <Container>
                <Row>
                    <Col className="d-flex align-items-center justify-content-between">
                        <h5 className="fw-bold">Expense</h5>
                        <div className="">
                            
                            <Button size="sm" className="fw-bold rounded py-1 px-3"
                         onClick={() => setShowCreateExpense(true)}   
                        >Create Expense</Button>
                        </div>
                        
                    </Col>
                </Row>
            </Container>
            <Modal show={showCreateExpense}
                            size="lg"
                            aria-labelledby="contained-modal-title-vcenter"
                            centered
                            onHide={() => setShowCreateExpense(false)}
                            >
                                
            
            <Modal.Header>
                <Modal.Title className='p-2'>
                Create new expense
                
            </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form onSubmit={addExpense}>
                    <Form.Group className='mb-2 '>
                        <Form.Label className='d-flex flex-row justify-content-between '>
                            Expense Name
                            {error.expenseName ? <p className='m-0 text-danger  '>*{error.expenseName}</p> : "" }
                        </Form.Label>
                        <Form.Control placeholder='enter expense name' type='text' onChange={(e) => setExpenseName(e.target.value)}/>
                     </Form.Group>
                    <Form.Group className='mb-2'>
                        <Form.Label className='d-flex flex-row justify-content-between '>
                           Amount
                           {error.amount ? <p className='m-0 text-danger  '>*{error.amount}</p> : "" }
                        </Form.Label>
                        <Form.Control placeholder='enter expense amount' type='number' onChange={(e) => setAmount(Number(e.target.value))}/>
                     </Form.Group>
                    <Form.Group className='mb-2'>
                        <Form.Label className='d-flex flex-row justify-content-between  '>
                            Date
                             {error.date ? <p className='m-0 text-danger  '>*{error.date}</p> : "" }
                        </Form.Label>
                        <Form.Control placeholder='enter expense name' type='date' onChange={(e) => setDate(e.target.value)}/>
                     </Form.Group>
                    <Form.Group className='mb-2'>
                        <Form.Label className='d-flex flex-row justify-content-between '>
                            Expense Type
                            {error.expenseType ? <p className='m-0 text-danger  '>*{error.expenseType}</p> : "" }
                        </Form.Label >
                                <Form.Select onChange={(e) => setExpenseType(e.target.value)}>
                                    <option value="">Open this select menu</option>
                                    <option value="FOOD">Food</option>
                                    <option value="TRANSPORTATION">Transportation</option>
                                    <option value="ACCOMMODATION">Accommodation</option>
                                    <option value="SHOPPING">Shopping</option>
                                    <option value="ENTERTAINMENT">Entertainment</option>
                                    <option value="SUBSCRIPTION">Subcription</option>
                                    <option value="OTHER">Other</option>     
                                    </Form.Select>
                     </Form.Group>
                    <Form.Group className='mb-2'>
                        <Form.Label>
                            Share To
                        </Form.Label>
                                <Form.Select onChange={(e) => setShareTarget(e.target.value)}>
                                    <option value="FRIEND">Friend</option>
                                    <option value="GROUP">Group</option>    
                                    </Form.Select>
                     </Form.Group>
                    <Form.Group className='mb-2'>
                        <Form.Label className='d-flex flex-row justify-content-between  '>
                            Choose friend or group you want to share
                            {error.targetId ? <p className='m-0 text-danger  '>*{error.targetId}</p> : "" }
                        </Form.Label>
                            
                                
                                {
                                shareTarget === "FRIEND" ? 
                                <SearchBar
                                   value={search}
                                    results={getTargetResult}
                                    itemKey={(item) => item.userId}
                                    itemLabel={(item) => item.username}
                                    onChange={setSearch}
                                    onSelect={(item) => {
                                         setStartSearch(false);
                                         setSearch(item.username);
                                         setTargetId(item.userId);
                                          setGetTargetResult([]);
                                    }}
                                    onClick={() => setStartSearch(true)}
                                   /> :
                                <SearchBar
                                    value={search}
                                    results={getTargetResult}
                                    itemKey={(item) => item.groupId}
                                    itemLabel={(item) => item.groupName}
                                    onChange={setSearch}
                                    onSelect={(item) => {
                                        setStartSearch(false);
                                        setSearch(item.groupName);
                                        setTargetId(item.groupId);
                                        setGetTargetResult([]);
                                    }}
                                    onClick={() => setStartSearch(true)}
                                   /> 
                               
                                }           
                           
                     </Form.Group>
                                 <div className="d-flex gap-2 mt-3">
                            <Button variant="secondary" onClick={() => setShowCreateExpense(false)} className="flex-fill">Cancel</Button>
                            <Button  className="flex-fill" type="submit" >Add</Button>
                        </div>
                </Form>
            </Modal.Body>
            </Modal>
          
                        </header>
                    <section>
                        <OweExpenseList/>
                    </section>
            
        </>
    )
}