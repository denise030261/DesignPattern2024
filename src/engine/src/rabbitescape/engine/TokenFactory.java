package rabbitescape.engine;

import rabbitescape.engine.token.BashToken;
import rabbitescape.engine.util.VariantGenerator;

    public class TokenFactory implements ThingFactory
    {
        @Override
        public Thing cloneThing(Thing thing) {
            if (!(thing instanceof Token)) {
                throw new IllegalArgumentException("Invalid type for PipeFactory");
            }
            Token token = (Token) thing;
            return Token.createToken(token.getType(), token.x, token.y);
        }
        
        @Override
        public Thing mapCreate(int x,int y,VariantGenerator variantGen)
        {
            return new BashToken(x, y);
        }
    }
