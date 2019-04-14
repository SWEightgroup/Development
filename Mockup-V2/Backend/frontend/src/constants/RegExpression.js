class RegExpression {
  static getRegEmail = () => {
    return /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(.\w{2,3})+$/;
  };

  static getRegPassword = () => {
    return /^(?=.*[A-Z])(?=.*[a-z])[a-zA-Z0-9*]{6,16}$/;
  };
}
export default RegExpression;
