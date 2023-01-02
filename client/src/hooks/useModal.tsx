import React, { useEffect, useState } from 'react';

const useModal = () => {
	const [isModal, setIsModal] = useState(false);
	const ref = React.useRef<HTMLDivElement>(null);

	const handleOutsideClick = (event: MouseEvent) => {
		if (ref.current && !ref.current.contains(event.target as Node)) {
			setIsModal(false);
		}
	};

	const handleModalChange = (value?: boolean) => {
		if (value) {
			setIsModal(value);
			return;
		}
		setIsModal((prev) => !prev);
	};

	useEffect(() => {
		document.addEventListener('click', handleOutsideClick);
		return () => {
			document.removeEventListener('click', handleOutsideClick);
		};
	}, []);

	return {
		isModal,
		ref,
		handleModalChange,
	};
};

export default useModal;
