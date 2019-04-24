import React from 'react';
import ReactDOM from 'react-dom';
import 'react-toastify/dist/ReactToastify.css';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { Provider } from 'react-redux';
// import { v4 } from 'node-uuid';
import { toast } from 'react-toastify';
import store from './store/index';

import App from './App';
import { register } from './serviceWorker';

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
