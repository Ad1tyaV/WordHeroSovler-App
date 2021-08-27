import GridComponent from "./components/GridComponent";
import ResultSet from './components/ResultSet';
import { Provider } from 'react-redux';
import store from './redux-setup/'

function App() {
  return (
    
      <div className="App">
        <Provider store = {store}>
          <GridComponent/>          
          <ResultSet/>
        </Provider>    
      </div>
    
  );
}

export default App;
