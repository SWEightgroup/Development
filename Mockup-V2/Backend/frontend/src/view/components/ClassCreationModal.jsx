import React from 'react';
import PropTypes from 'prop-types';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Checkbox from './Checkbox';
import _translator from '../../helpers/Translator';

class ClassCreationModal extends React.Component {
  constructor(props) {
    super(props);
    this.errorName = React.createRef();
    this.errorList = React.createRef();
  }

  handleChangeCheck = e => {
    e.preventDefault();
    const { updateList, students } = this.props;
    updateList(students, e.target.name);
  };

  handleChangeName = e => {
    e.preventDefault();
    const { language } = this.props;
    if (e.target.value === '') {
      this.errorName.current.innerText = _translator('gen_reqField', language);
    } else this.errorName.current.innerText = '';
    const { onChangeName } = this.props;
    onChangeName(e.target.value);
  };

  onConfirm = ({ name, students: studentsToReturn, teacherId }) => {
    const { confirm, language } = this.props;
    if (!name || name.length === 0) {
      this.errorName.current.innerText = _translator('gen_reqField', language);
    } else if (studentsToReturn && studentsToReturn.length === 0) {
      this.errorList.current.innerText = _translator('gen_reqField', language);
    } else {
      this.errorName.current.innerText = '';
      this.errorList.current.innerText = '';
      confirm({ name, students: studentsToReturn, teacherId });
    }
  };

  render() {
    const {
      title,
      language,
      name,
      show,
      setShow,
      students,
      teacherId
    } = this.props;

    const studentsToReturn = students
      .filter(student => student.isSelected === true)
      .map(student => student.key);

    return (
      <ButtonToolbar>
        <Modal
          size="lg"
          show={show}
          onHide={() => setShow(false)}
          aria-labelledby="example-modal-sizes-title-lg"
        >
          <Modal.Header closeButton>
            <Modal.Title id="example-modal-sizes-title-lg">{title}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Container>
              <Row>
                <Col>
                  <div className="position-relative form-group">
                    <input
                      name="name"
                      placeholder={_translator(
                        'classCreationModal_name',
                        language
                      )}
                      type="text"
                      className="form-control"
                      value={name}
                      onChange={this.handleChangeName}
                    />
                    <span className="text-danger" ref={this.errorName} />
                  </div>
                </Col>
              </Row>
              <Row>
                <Col sm={6} className="border-bottom">
                  <div className="scroll-area-lg">
                    <div className="scrollbar-container">
                      <h6>
                        {_translator(
                          'classCreationModal_allStudents',
                          language
                        )}
                      </h6>
                      <ul className="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
                        {students
                          .filter(item => item.isSelected !== true)
                          .map(student => {
                            return (
                              <li
                                key={`li-${student.name}`}
                                className="list-group-item "
                              >
                                <div className="widget-content p-0">
                                  <div className="widget-content-wrapper ">
                                    <div className="widget-content-left mr-3">
                                      <Checkbox
                                        id={student.name}
                                        name={student.name}
                                        onChange={this.handleChangeCheck}
                                        checked={student.isSelected}
                                      />
                                    </div>
                                    <div className="widget-content-left">
                                      <label htmlFor={student.name}>
                                        <div className="widget-heading">
                                          {student.label}
                                        </div>
                                        <div className="widget-subheading">
                                          {student.name}
                                        </div>
                                      </label>
                                    </div>
                                  </div>
                                </div>
                              </li>
                            );
                          })}
                      </ul>
                    </div>
                  </div>
                </Col>

                <Col sm={6} className="border-bottom">
                  <div className="scroll-area-lg">
                    <div className="scrollbar-container">
                      <h6>
                        {_translator(
                          'classCreationModal_selStudents',
                          language
                        )}
                      </h6>
                      <ul className="rm-list-borders rm-list-borders-scroll list-group list-group-flush">
                        {students
                          .filter(item => item.isSelected === true)
                          .map(student => (
                            <li
                              key={`li-${student.name}`}
                              className="list-group-item"
                            >
                              <div className="widget-content p-0">
                                <div className="widget-content-wrapper">
                                  <div className="widget-content-left mr-3">
                                    <Checkbox
                                      id={student.name}
                                      name={student.name}
                                      onChange={this.handleChangeCheck}
                                      checked={student.isSelected}
                                    />
                                  </div>
                                  <div className="widget-content-left">
                                    <label htmlFor={student.name}>
                                      <div className="widget-heading">
                                        {student.label}
                                      </div>
                                      <div className="widget-subheading">
                                        {student.name}
                                      </div>
                                    </label>
                                  </div>
                                </div>
                              </div>
                            </li>
                          ))}
                      </ul>
                    </div>
                  </div>
                </Col>
                <span className="text-danger" ref={this.errorList} />
              </Row>
            </Container>
          </Modal.Body>
          <Modal.Footer>
            <ButtonToolbar>
              <Button
                onClick={() =>
                  this.onConfirm({
                    name,
                    students: studentsToReturn,
                    teacherId
                  })
                }
              >
                {_translator('gen_save', language)}
              </Button>
            </ButtonToolbar>
          </Modal.Footer>
        </Modal>
      </ButtonToolbar>
    );
  }
}

ClassCreationModal.propTypes = {
  students: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string,
      key: PropTypes.string,
      label: PropTypes.string,
      isSelected: PropTypes.bool
    })
  ),
  title: PropTypes.string.isRequired,
  confirm: PropTypes.func.isRequired,
  name: PropTypes.string,
  language: PropTypes.string.isRequired,
  onChangeName: PropTypes.func.isRequired,
  show: PropTypes.bool.isRequired,
  setShow: PropTypes.func.isRequired
};

ClassCreationModal.defaultProps = {
  students: [],
  name: ''
};
export default ClassCreationModal;
