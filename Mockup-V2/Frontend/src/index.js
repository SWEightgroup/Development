import React from 'react';
import ReactDOM from 'react-dom';
import 'react-toastify/dist/ReactToastify.css';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
// import { v4 } from 'node-uuid';
import { toast } from 'react-toastify';
import rootReducer from './reducers/RootReducer';
import App from './App';
import { register } from './serviceWorker';

export const store = createStore(rootReducer, applyMiddleware(thunk));

toast.configure();

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
register();

// });

// PROMEMORIA ->  FILE MAIN.JS, RIGA 26935 COMMENTATO PER ERRORE IN CONSOLE
