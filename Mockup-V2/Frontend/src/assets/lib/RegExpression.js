class RegExpression {
  regMail = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(.\w{2,3})+$/;
  regPassword = /^(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9*]{6,16}$/;

  getRegEmail = () => {
    return this.regMail;
  };

  getRegPassword = () => {
    return this.regPassword;
  };
}
export default RegExpression;
