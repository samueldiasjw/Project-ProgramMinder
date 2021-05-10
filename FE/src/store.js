import { configureStore } from '@reduxjs/toolkit'
import { combineReducers } from "redux";

import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

import usersReducer from "./reducers/users";

const persistConfig = {
    key: 'root',
    storage
}

const persistedReducer = persistReducer(persistConfig, usersReducer);

const reducer = combineReducers({
  users: persistedReducer
})

const store = configureStore({
  reducer,
  devTools: true
})

const persistor = persistStore(store)

export {store, persistor};