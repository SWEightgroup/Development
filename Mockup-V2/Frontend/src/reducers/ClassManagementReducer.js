const initState = {
  classList: [],
  studentsList: [],
  showModalClass: false,
  newClass: {
    name: '',
    studentsList: [],
    id: null
  }
};

const ClassManagementReducer = (state = initState, action) => {
  switch (action.type) {
    case 'LOAD_CLASS_LIST_SUCCESS':
      return {
        ...state,
        classList: action.obj.classList
      };
    case 'UPDATE_NAMENEWCLASS_SUCCESS':
      return {
        ...state,
        newClass: {
          ...state.newClass,
          name: action.name
        }
      };
    case 'SET_SHOWMODALCLASS':
      return {
        ...state,
        showModalClass: action.flag
      };

    case 'UPDATE_LISTNEWCLASS_SUCCESS':
      return {
        ...state,
        newClass: {
          ...state.newClass,
          studentsList: action.list
        }
      };
    case 'CREATE_SUCCESS':
      return {
        ...state,
        newClass: {
          ...initState.newClass,
          studentsList: action.studentsList
        },
        showModalClass: false
      };
    case 'UPDATE_STUDENT_LIST':
      return {
        ...state,
        studentsList: action.studentsList
      };
    case 'UPDATE_STUDENTSLIST_NEWCLASS':
      return {
        ...state,
        newClass: {
          ...state.newClass,
          studentsList: action.studentsList
        }
      };
    case 'INIT_EDIT_CLASS':
      return {
        ...state,
        newClass: {
          ...initState.newClass,
          studentsList: action.class.studentsList,
          name: action.class.name,
          id: action.class.id
        },
        showModalClass: true
      };
    default:
      // console.error('REDUCER ERRATO', state, action);
      return { ...state };
  }
};

export default ClassManagementReducer;
