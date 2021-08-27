const getWordsAction = (boardData) => {

    // console.log(boardData);

    // return {
    //     type:'Update',
    //     payload:boardData
    // }

    return async (dispatch, getState)=>  {

        const data = await fetch("http://localhost:8090/solveBoard/", { method:'POST', headers:{"Content-Type":'text/plain'}, body:boardData});
        const response = await data.json();

        dispatch({type:'Update', payload:response})
    }

}

export default getWordsAction;