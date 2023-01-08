import python
private import semmle.python.RegexTreeView::RegexTreeView as TreeView
import codeql.regex.nfa.SuperlinearBackTracking::Make<TreeView>

from PolynomialBackTrackingTerm t
select t.(TreeView::RegExpTerm).getRegex(), t, t.getReason()
