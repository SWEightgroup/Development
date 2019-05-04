import axios from 'axios';
import store from '../store/index';
import { _toastSuccess, _toastError } from '../helpers/Utils';
import _translator from '../helpers/Translator';

export const loadClassList = () => {
  return dispatch => {
    if (store.getState().auth.token !== null) {
      axios
        .get('http://localhost:8081/class/', {
          headers: {
            Authorization: store.getState().auth.token
          }
        })
        .then(res => {
          dispatch({
            type: 'LOAD_CLASS_LIST_SUCCESS',
            obj: {
              classList: res.data
            }
          });
        })
        .catch(error => {
          console.error(error);
        });
    } else {
      dispatch(store.dispatch.signOut());
    }
  };
};

export const createClass = ({ name, students, teacherId }) => {
  return dispatch => {
    axios
      .post(
        'http://localhost:8081/class/create',
        {
          name,
          studentsId: students,
          teacherId
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(res => {
        _toastSuccess(
          _translator('gen_opSuccess', store.getState().auth.user.language)
        );
        dispatch({
          type: 'CREATE_SUCCESS',
          studentsList: store.getState().class.studentsList
            ? store.getState().class.studentsList.map(student => {
                return {
                  label: `${student.firstName} ${student.lastName}`,
                  key: student.id,
                  name: student.username,
                  isSelected: false
                };
              })
            : []
        });
        dispatch(loadClassList());
      })
      .catch(err => {
        console.error(err);
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
      });
  };
};

export const updateNameNewClass = name => {
  return dispatch => {
    dispatch({ type: 'UPDATE_NAMENEWCLASS_SUCCESS', name });
  };
};

export const setShowModalClass = flag => {
  return dispatch => {
    dispatch({ type: 'SET_SHOWMODALCLASS', flag });
  };
};

export const setShowModalEditClass = flag => {
  return dispatch => {
    dispatch({ type: 'SET_SHOWMODAL_EDITCLASS', flag });
  };
};

export const updateListNewClass = (list, name) => {
  return dispatch => {
    const element = list.find(item => item.name === name);
    if (element !== undefined) element.isSelected = !element.isSelected;
    dispatch({ type: 'UPDATE_LISTNEWCLASS_SUCCESS', list });
  };
};

export const initListNewClass = list => {
  return dispatch => {
    dispatch({ type: 'UPDATE_LISTNEWCLASS_SUCCESS', list });
  };
};

export const updateStudentsList = studentsList => {
  return dispatch => {
    dispatch({ type: 'UPDATE_STUDENT_LIST', studentsList });
  };
};

export const getAllStudents = () => {
  return dispatch => {
    axios
      .get('http://localhost:8081/users/get-students', {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(resGetStudent => {
        dispatch(updateStudentsList(resGetStudent.data));
        dispatch({
          type: 'UPDATE_STUDENTSLIST_NEWCLASS',
          studentsList: resGetStudent.data
            ? resGetStudent.data.map(student => {
                return {
                  label: `${student.firstName} ${student.lastName}`,
                  key: student.id,
                  name: student.username,
                  isSelected: false
                };
              })
            : []
        });
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const getAllTeachers = _link => {
  const link =
    _link !== null && _link !== undefined
      ? _link.href
      : 'http://localhost:8081/users/ROLE_TEACHER';
  return dispatch => {
    axios
      .get(link, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({
          type: 'GET_TEACHERS_LIST',
          teachersList: res.data
        });
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const getFavouriteTeachers = () => {
  return dispatch => {
    axios
      .get('http://localhost:8081/students/favorite-teacher', {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(res => {
        dispatch({
          type: 'GET_FAVOURITE_TEACHERS',
          favouriteTeachers: res.data
        });
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const addFavouriteTeachers = teacher => {
  return dispatch => {
    const _class = store.getState().class;
    const { favouriteTeachers } = _class;
    let teacherIndex = favouriteTeachers.findIndex(
      _teacher => _teacher.id === teacher.id
    );
    if (teacherIndex === -1) favouriteTeachers.push(teacher);
    dispatch({
      type: 'GET_FAVOURITE_TEACHERS',
      favouriteTeachers
    });
    if (teacherIndex === -1) {
      axios
        .post(
          `http://localhost:8081/students/favorite-teacher/${teacher.id}`,
          {},
          {
            headers: {
              Authorization: store.getState().auth.token
            }
          }
        )
        .then(() => {
          // dispatch(getFavouriteTeachers());
        })
        .catch(() => {
          teacherIndex = favouriteTeachers.findIndex(
            _teacher => _teacher.id === teacher.id
          );
          if (teacherIndex > -1) favouriteTeachers.splice(teacherIndex, 1);
          dispatch({
            type: 'GET_FAVOURITE_TEACHERS',
            favouriteTeachers
          });
          _toastError(
            _translator('gen_error', store.getState().auth.user.language)
          );
          return dispatch({ type: '' });
        });
    }
  };
};

export const removeFavouriteTeachers = teacherId => {
  return dispatch => {
    const _class = store.getState().class;
    const { favouriteTeachers } = _class;
    const teacherIndex = favouriteTeachers.findIndex(
      teacher => teacher.id === teacherId
    );
    let removedElement = null;
    if (teacherIndex > -1) {
      removedElement = favouriteTeachers.splice(teacherIndex, 1);
    }
    dispatch({
      type: 'GET_FAVOURITE_TEACHERS',
      favouriteTeachers
    });
    axios
      .delete(`http://localhost:8081/students/favorite-teacher/${teacherId}`, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(() => {
        // dispatch(getFavouriteTeachers());
      })
      .catch(() => {
        if (teacherIndex > -1) {
          favouriteTeachers.splice(teacherIndex, 0, removedElement);
        }
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};

export const initEditClass = ({ id, name, students }) => {
  const studentsList = store.getState().class.studentsList
    ? store.getState().class.studentsList.map(student => {
        return {
          label: `${student.firstName} ${student.lastName}`,
          key: student.id,
          name: student.username,
          isSelected: false
        };
      })
    : [];
  if (students) {
    students.forEach(_student => {
      const element = studentsList.find(student => student.key === _student.id);
      if (element !== undefined) {
        element.isSelected = true;
      }
    });
  }
  return dispatch => {
    dispatch({ type: 'INIT_EDIT_CLASS', class: { id, name, studentsList } });
  };
};

export const editClass = ({ students, name }) => {
  return dispatch => {
    axios
      .put(
        'http://localhost:8081/class/modify',
        {
          className: name,
          studentsId: students,
          classId: store.getState().class.newClass.id
        },
        {
          headers: {
            'content-type': 'application/json',
            Authorization: store.getState().auth.token
          }
        }
      )
      .then(() => {
        _toastSuccess(
          _translator('gen_opSuccess', store.getState().auth.user.language)
        );
        dispatch({
          type: 'CREATE_SUCCESS',
          studentsList: store.getState().class.studentsList
            ? store.getState().class.studentsList.map(student => {
                return {
                  label: `${student.firstName} ${student.lastName}`,
                  key: student.id,
                  name: student.username,
                  isSelected: false
                };
              })
            : []
        });
        dispatch(loadClassList());
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
      });
  };
};

export const deleteClass = ({ classId }) => {
  return dispatch => {
    axios
      .delete(`http://localhost:8081/class/${classId}`, {
        headers: {
          Authorization: store.getState().auth.token
        }
      })
      .then(() => {
        _toastSuccess(
          _translator('gen_opSuccess', store.getState().auth.user.language)
        );
        dispatch(loadClassList());
      })
      .catch(() => {
        _toastError(
          _translator('gen_error', store.getState().auth.user.language)
        );
        return dispatch({ type: '' });
      });
  };
};
