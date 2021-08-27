import React from 'react'
import './gridStyles.css'
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux'
import getWordsAction from '../redux-setup/actions/getWordsAction';

function GridComponent() {

    const prePopulate = ['S', 'S', 'E', 'E', 'H', 'P', 'R', 'D', 'I', 'A', 'E', 'I', 'S', 'D', 'Y', 'R']    

    const dispatch = useDispatch()
    const apiData = useSelector(state => {return state.board})
    
    const dispatchCaller = () => {
        let boardData = "";
        Array.from(document.getElementsByTagName("input")).forEach(element=>{            
            // boardData.push(element.value);             
            boardData+=element.value.toUpperCase();
        });
        // console.log("Up here"+boardData);
        // dispatch(boardData);
        dispatch(getWordsAction(boardData));
        console.log(apiData);

    }

    useEffect(()=>{

        let pointer = 0;
        Array.from(document.getElementsByTagName("input")).forEach(element=>{
            element.value = prePopulate[pointer++];
        });

    },[])

    return (
        <div>
            
            <table style={{borderColor:"whitesmoke"}}>
                <tr>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                </tr>

                <tr>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                </tr>

                <tr>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                </tr>
                <tr>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                    <td><input type="text" width="fitContent" maxLength="1"></input></td>
                </tr>
            </table>

            <div style={{display:"flex", flexDirection:"row", alignContent:"center", justifyContent:"center", marginTop:"1%"}}>            
                <button onClick={()=>{dispatchCaller()}}>SOLVE</button>
            </div>

            {
                // apiData.data==null?<h3>Fetching Data</h3>:<h6 style={{color:"white"}}>{JSON.stringify(apiData.data)}</h6>                

                apiData.data?.errorMessage==null?
                apiData.data==null?<h3 style={{color:"white"}}>Fetching Data</h3>:<h6 style={{color:"white"}}>
                    {           
                        
                        
                        Object.keys(apiData.data.words).map(length=>(
                            
                            <>
                                <h2 style={{marginLeft:"1%"}}>{`Words of Length - ${length}`}</h2>
                                {
                                    <span style={{marginLeft:'1%'}}>
                                        {
                                            Object.keys(apiData.data.words[length]).map(index=>(
                                                // <p>{`${apiData.data.words[length][index]} `}</p>
                                                <span style={{fontSize:"0.72rem"}}>{`${apiData.data.words[length][index]} `}</span>
                                            ))
                                        }
                                    </span>
                                    // JSON.stringify(apiData.data.words[length])
                                }
                                
                                
                                {/* <h4 style={{marginLeft:"1%", whiteSpace:'normal'}}>{JSON.stringify(apiData.data.words[length])}</h4> */}
                            </>
                            
                            
                        ))
                        

                    }
                </h6>
                :<h5 style={{color:"whitesmoke", textAlign:"center"}}>{'Recheck your input'}</h5>
            }

        </div>
    )
}



export default GridComponent
