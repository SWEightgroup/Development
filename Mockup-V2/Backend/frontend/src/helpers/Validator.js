class Validator {
  static validEmail = (email, regMail) =>
    email !== null && email !== '' && email.match(regMail);

  static validDate = date => {
    return (
      date !== null && new Date(date).getTime() && new Date(date) < new Date()
    );
  };

  static validSelect = (option, array) => {
    return option && option !== '' && array.find(item => item === option);
  };

  static validPassword = (pwd, regPassword) =>
    pwd !== null && pwd !== '' && pwd.match(regPassword);
}

export default Validator;
