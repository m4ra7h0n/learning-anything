import javascript

class XSSTracker extends TaintTracking::Configuration {
    XSSTracker() {
      // unique identifier for this configuration
      this = "XSSTracker"
    }
  
    override predicate isSource(DataFlow::Node nd) {
     exists(CallExpr dollarCall |
        nd.asExpr() instanceof CallExpr and
        dollarCall.getCalleeName() = "split" and
        dollarCall.getReceiver().toString() = "location.hash" and
        nd.asExpr() = dollarCall
      ) 
    }
  
    override predicate isSink(DataFlow::Node nd) {
      exists(CallExpr dollarCall |
        dollarCall.getCalleeName() = "write" and
        dollarCall.getReceiver().toString() = "document" and
        nd.asExpr() = dollarCall.getArgument(0)
      )
    }
  }
  
  from XSSTracker pt, DataFlow::Node source, DataFlow::Node sink
  where pt.hasFlow(source, sink)
  select source,sink