import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import { Provider } from 'react-redux';
import rootReducer from './store/reducers/RootReducer';
import App from './App';
import { register } from './serviceWorker';

const store = createStore(rootReducer, compose(applyMiddleware(thunk)));

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
);
register();

// });

// PROMEMORIA ->  FILE MAIN.JS, RIGA 26935 COMMENTATO PER ERRORE IN CONSOLE
