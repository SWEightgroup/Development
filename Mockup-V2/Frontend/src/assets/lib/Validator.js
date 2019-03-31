class Validator {
  checkItem = (item, re) => {
    return item.match(re);
  };

  validEmail = (email, regMail) =>
    email !== null && email !== '' && this.checkItem(email, regMail);

  validDate = date => {
    return (
      date !== null && new Date(date).getTime() && new Date(date) < new Date()
    );
  };

  validSelect = (option, array) => {
    return option && option !== '' && array.find(item => item === option);
  };

  validPassword = (pwd, regPassword) =>
    pwd !== null && pwd !== '' && this.checkItem(pwd, regPassword);
}

export default Validator;
