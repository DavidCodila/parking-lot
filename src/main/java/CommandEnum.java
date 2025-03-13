public enum CommandEnum {
    PARK_CAR {
        @Override
        public Command createCommand(int parameter) {
            return new ParkCarCommand(parameter);
        }
    },
    FIND {
        @Override
        public Command createCommand(int parameter) {
            return new FindCarCommand(parameter);
        }
    },
    UN_PARK_CAR {
        @Override
        public Command createCommand(int parameter) {
            return new UnParkCarCommand(parameter);
        }
    },
    //need to implement
    LIST {
        @Override
        public Command createCommand(int parameter) {
            return null;
        }
    };

    public abstract Command createCommand(int parameter);
}
