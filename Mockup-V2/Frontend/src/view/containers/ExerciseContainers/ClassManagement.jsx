import React from 'react';
import { connect } from 'react-redux';
import Button from 'react-bootstrap/Button';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import _translator from '../../../helpers/Translator';
import { _confirmAlert } from '../../../helpers/Utils';
import ClassCreationModal from '../../components/ClassCreationModal';
import {
  loadClassList,
  updateNameNewClass,
  setShowModalClass,
  initListNewClass,
  updateListNewClass,
  createClass,
  getAllStudents,
  initEditClass,
  editClass,
  deleteClass
} from '../../../actions/ClassManagementActions';

class ClassManagement extends React.Component {
  constructor(props) {
    super(props);
    props.getAllStudentsDispatch();
    props.loadClassListDispatch();
  }

  render() {
    const {
      user,
      updateNameNewClassDispatch,
      setShowModalClassDispatch,
      initListNewClassDispatch,
      updateListNewClassDispatch,
      createClassDispatch,
      initEditClassDispatch,
      editClassDispatch,
      deleteClassDispatch,
      _class
    } = this.props;
    const { newClass, classList } = _class;
    const { language } = user;
    return (
      <div className="row">
        <div className="col-md-12">
          <div className="main-card mb-3 card">
            <div className="card-header">
              {_translator('classManagement_title', language)}
              <div className="btn-actions-pane-right">
                <div role="group" className="btn-group-sm btn-group">
                  <button
                    type="button"
                    className="active btn btn-success"
                    onClick={() => {
                      initListNewClassDispatch(newClass.studentsList);
                      setShowModalClassDispatch(true);
                    }}
                  >
                    {_translator('gen_new', language)}{' '}
                    <i className="fa fa-plus" />
                  </button>
                </div>
              </div>
            </div>
            <div className="row">
              <div className="col-md-12">
                <div id="accordion" className="accordion-wrapper">
                  {classList &&
                    classList.length > 0 &&
                    classList.map(classItem => {
                      return (
                        <div className="card" key={classItem.classId}>
                          <div id="headingOne" className="card-header">
                            <button
                              type="button"
                              data-toggle="collapse"
                              data-target={`#c${classItem.classId}`}
                              aria-expanded="true"
                              aria-controls="collapseOne"
                              className="text-left m-0 p-0 btn btn-link btn-block"
                            >
                              <h5 className="m-0 p-0">{classItem.className}</h5>
                            </button>
                          </div>
                          <div
                            data-parent="#accordion"
                            id={`c${classItem.classId}`}
                            aria-labelledby="headingOne"
                            className="collapse "
                          >
                            <div className="card-body">
                              <ul>
                                {classItem &&
                                  classItem.students.map(student => (
                                    <li key={student.id}>
                                      {student.firstName} {student.lastName}
                                    </li>
                                  ))}
                              </ul>

                              <ButtonGroup
                                className="pull-right pb-2 pt-1 pr-0"
                                aria-label="Edit Button "
                              >
                                <Button
                                  variant="warning"
                                  onClick={() =>
                                    initEditClassDispatch({
                                      id: classItem.classId,
                                      name: classItem.className,
                                      students: classItem.students
                                    })
                                  }
                                >
                                  {_translator('gen_edit', language)}
                                </Button>
                                <Button
                                  variant="danger"
                                  onClick={() =>
                                    _confirmAlert(
                                      {
                                        message:
                                          'Sei sicuro di voler approvare?'
                                      },
                                      deleteClassDispatch,
                                      { classId: classItem.classId }
                                    )
                                  }
                                >
                                  {_translator('gen_delete', language)}
                                </Button>
                              </ButtonGroup>
                            </div>
                          </div>
                        </div>
                      );
                    })}
                </div>
              </div>
            </div>
          </div>
        </div>
        {newClass.studentsList && newClass.studentsList.length > 0 && (
          <ClassCreationModal
            students={newClass.studentsList}
            title="Crea una nuova classe"
            confirm={
              newClass.id !== null ? editClassDispatch : createClassDispatch
            }
            name={newClass.name}
            language="it"
            onChangeName={updateNameNewClassDispatch}
            show={_class.showModalClass}
            setShow={setShowModalClassDispatch}
            updateList={updateListNewClassDispatch}
            teacherName={`${user.firstName} ${user.lastName}`}
          />
        )}
      </div>
    );
  }
}

const mapStateToProps = store => {
  return {
    user: store.auth.user,
    _class: store.class
  };
};

const mapDispatchToProps = dispatch => {
  return {
    loadClassListDispatch: () => dispatch(loadClassList()),
    updateNameNewClassDispatch: name => dispatch(updateNameNewClass(name)),
    setShowModalClassDispatch: flag => dispatch(setShowModalClass(flag)),
    getAllStudentsDispatch: () => dispatch(getAllStudents()),
    initListNewClassDispatch: list => dispatch(initListNewClass(list)),
    updateListNewClassDispatch: (list, name) =>
      dispatch(updateListNewClass(list, name)),
    createClassDispatch: ({ name, students, teacherName }) =>
      dispatch(createClass({ name, students, teacherName })),
    initEditClassDispatch: selectedClass =>
      dispatch(initEditClass(selectedClass)),
    editClassDispatch: ({ students, name }) =>
      dispatch(editClass({ students, name })),
    deleteClassDispatch: classId => dispatch(deleteClass(classId))
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ClassManagement);
