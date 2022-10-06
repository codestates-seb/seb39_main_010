import { atom, AtomEffect } from 'recoil';
import { recoilPersist } from 'recoil-persist';
import { User } from 'types';

type RecoilPersist<T> = {
	persistAtom: AtomEffect<T>;
};

// loginModal
export const loginModalAtom = atom({ key: 'loginMoalAtom', default: false });

// user
export const { persistAtom: persistUserAtom } = recoilPersist({
	key: 'persistUserAtom',
	storage: localStorage,
}) as RecoilPersist<User | null>;

export const userAtom = atom({
	key: 'userAtom',
	default: null,
	effects_UNSTABLE: [persistUserAtom],
});
