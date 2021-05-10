const initialState = [];

function usersReducer(state = initialState, action) {
  switch (action.type) {
    case "ADD_LOGGED_USER_INFO":
      return action.payload
    default:
      return state
  }
}

export default usersReducer;