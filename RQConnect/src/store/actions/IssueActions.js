export const reassignIssue = issue => {
  return (dispatch, getState, { getFirestore }) => {
    const firestore = getFirestore();
    firestore
      .collection("issues")
      .doc(issue.id)
      .update({
        userSelectedId: issue.userSelectedId,
        userSelectedName: issue.userSelectedName
      })
      .then(() => {
        dispatch({ type: "REASSIGN_ISSUE_SUCCESS" });
      })
      .catch(err => {
        dispatch({ type: "REASSIGN_ISSUE_ERROR" }, err);
      });
  };
};
export const updateUC = issue => {
  return (dispatch, getState, { getFirestore }) => {
    const firestore = getFirestore();
    firestore
      .collection("usecase")
      .doc(issue.id)
      .update({
        lista: issue.lista
      })
      .then(() => {
        dispatch({ type: "UPDATE_UC_SUCCESS" });
      })
      .catch(err => {
        dispatch({ type: "UPDATE_UC_ERROR" }, err);
      });
  };
};

export const createUC = issue => {
  return (dispatch, getState, { getFirestore }) => {
    const firestore = getFirestore();
    const authorId = getState().firebase.auth.uid;
    firestore
      .collection("usecase")
      .add({
        ...issue,
        authorId: authorId,
        createdAt: new Date()
      })
      .then(() => {
        dispatch({ type: "CREATE_UC_SUCCESS" });
      })
      .catch(err => {
        console.log(err);
        dispatch({ type: "CREATE_UC_ERROR" }, err);
      });
  };
};
