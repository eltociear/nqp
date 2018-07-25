package org.perl6.nqp.truffle.nodes.expression;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import org.perl6.nqp.truffle.nodes.NQPNode;
import org.perl6.nqp.truffle.nodes.NQPIntNode;
import org.perl6.nqp.dsl.Deserializer;
import org.perl6.nqp.truffle.runtime.NQPHash;
import org.perl6.nqp.truffle.runtime.NQPList;
import org.perl6.nqp.truffle.Debug;

@NodeInfo(shortName = "elems")
public final class NQPElemsNode extends NQPIntNode {
    @Child private NQPNode argNode;

    @Deserializer
    public NQPElemsNode(NQPNode argNode) {
        this.argNode = argNode;
    }

    @Override
    public long executeInt(VirtualFrame frame) {
        Object arg = argNode.execute(frame);
        if (arg instanceof NQPList) {
            return ((NQPList)arg).elems();
        } else if (arg instanceof NQPHash) {
            return ((NQPHash)arg).elems();
        } else {
            throw Debug.wrongThing("does not implement elems", arg);
        }
    }
}