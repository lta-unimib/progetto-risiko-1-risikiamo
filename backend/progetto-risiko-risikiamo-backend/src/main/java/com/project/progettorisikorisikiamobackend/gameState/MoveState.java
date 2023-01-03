package main.java.com.project.progettorisikorisikiamobackend.gameState;

public class MoveState  extends GameState{
    
        public MoveState(GameStatus gameStatus, Turn turno) {
            super(gameStatus, turno);
            // TODO Auto-generated constructor stub
        }
    
        @Override
        public void sposta() {
            // TODO Auto-generated method stub
            super.getTurno().getCurrentPlayer().moveArmies();
        }
    
        @Override
        public void attacca() {
            // TODO Auto-generated method stub
            Logger logger = new Logger();
            logger.config("non è possibile effettuare attacchi in questo stato");
        }
    
        @Override
        public void rinforza() {
            // TODO Auto-generated method stub
            Logger logger = new Logger();
            logger.config("non è possibile effettuare rinforzi in questo stato");
        }
}



