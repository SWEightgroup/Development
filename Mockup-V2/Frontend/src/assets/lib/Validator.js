const regMail = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(.\w{2,3})+$/;
const regPassword = /^(?=.*[A-Z])(?=.*[a-z])[a-zA-Z*]{6,16}$/;

const checkItem = (item, re) => {
  return item.match(re);
};

export const validEmail = email =>
  email !== null && email !== '' && checkItem(email, regMail);

export const validDate = date => {
  return (
    date !== null && new Date(date).getTime() && new Date(date) < new Date()
  );
};

export const validSelect = (option, array) => {
  console.log(': option, array', option, array);
  return option && option !== '' && array.find(item => item === option);
};

export const validPassword = pwd =>
  pwd !== null && pwd !== '' && checkItem(pwd, regPassword);
