import ReactDOM from 'react-dom';
import './Modal.css';

function Modal({ children, onClose }) {
  return ReactDOM.createPortal(
    <>
      <div className="sm-modal-backdrop" onClick={onClose} />
      <div className="sm-modal-content">
        <button className="sm-modal-close" onClick={onClose}>X</button>
        {children}
      </div>
    </>,
    document.getElementById('modal-root')
  );
}

export default Modal;
