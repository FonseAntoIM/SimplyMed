import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import { RecetaProvider } from './context/RecetaContext';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RecetaProvider>
      <App />
    </RecetaProvider>
  </React.StrictMode>
);
