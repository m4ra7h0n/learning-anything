// generated by codegen/codegen.py
private import codeql.swift.generated.Synth
private import codeql.swift.generated.Raw
import codeql.swift.elements.expr.Expr
import codeql.swift.elements.decl.VarDecl

module Generated {
  class RebindSelfInConstructorExpr extends Synth::TRebindSelfInConstructorExpr, Expr {
    override string getAPrimaryQlClass() { result = "RebindSelfInConstructorExpr" }

    /**
     * Gets the sub expression of this rebind self in constructor expression.
     *
     * This includes nodes from the "hidden" AST. It can be overridden in subclasses to change the
     * behavior of both the `Immediate` and non-`Immediate` versions.
     */
    Expr getImmediateSubExpr() {
      result =
        Synth::convertExprFromRaw(Synth::convertRebindSelfInConstructorExprToRaw(this)
              .(Raw::RebindSelfInConstructorExpr)
              .getSubExpr())
    }

    /**
     * Gets the sub expression of this rebind self in constructor expression.
     */
    final Expr getSubExpr() { result = getImmediateSubExpr().resolve() }

    /**
     * Gets the self of this rebind self in constructor expression.
     *
     * This includes nodes from the "hidden" AST. It can be overridden in subclasses to change the
     * behavior of both the `Immediate` and non-`Immediate` versions.
     */
    VarDecl getImmediateSelf() {
      result =
        Synth::convertVarDeclFromRaw(Synth::convertRebindSelfInConstructorExprToRaw(this)
              .(Raw::RebindSelfInConstructorExpr)
              .getSelf())
    }

    /**
     * Gets the self of this rebind self in constructor expression.
     */
    final VarDecl getSelf() { result = getImmediateSelf().resolve() }
  }
}