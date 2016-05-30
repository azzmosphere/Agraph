package azzmosphere.agraph.edge;

/**
 * Created by aaron.spiteri on 30/05/2016.
 */
public enum Axis implements AxisIface {
    XAXIS {
        @Override
        public int getBitMask() {
            return 0x000000001;
        }
    },
    YAXIS {
        @Override
        public int getBitMask() {
            return 0x000000010;
        }
    },
    ZAXIS {
        @Override
        public int getBitMask() {
            return 0x000000100;
        }
    };
}
