package rabbitescape.engine;

import rabbitescape.engine.util.VariantGenerator;

    public class TokenFactory implements ThingFactory
    {
        private Token.Type tokenType;

        public TokenFactory(Token.Type tokenType) {
            this.tokenType = tokenType;
        }
        
        @Override
        public Thing cloneThing(Thing thing) {
            if (!(thing instanceof Token)) {
                throw new IllegalArgumentException("Invalid type for TokenFactory");
            }
            Token token = (Token) thing;
            return Token.createToken(token.getType(), token.x, token.y);
        }
        
        @Override
        public Thing mapCreate(int x, int y, VariantGenerator variantGen) {
             return Token.createToken(tokenType, x, y);
        }
    }
