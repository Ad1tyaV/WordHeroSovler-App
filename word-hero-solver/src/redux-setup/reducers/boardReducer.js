const boardData = {
    data:null
}


const boardReducer = (state = boardData, action) => {

    // console.log(action);

    switch(action.type){

        case 'Update':{

            console.log(action.payload);

            return {
                ...state, data:action.payload
            };

            
        }

        default:
            return boardData;

    }   

}

export default boardReducer;