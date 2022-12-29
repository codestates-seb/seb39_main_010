import { useEffect, useRef, useState } from 'react';

const useInfiniteScroll = (callback: () => void) => {
	const [observationTarget, setObservationTarget] =
		useState<HTMLDivElement | null>(null);

	const observer = useRef(
		new IntersectionObserver(
			([entry]) => {
				if (!entry.isIntersecting) return;
				callback();
			},
			{ threshold: 1 }
		)
	);

	useEffect(() => {
		const currentTarget = observationTarget;
		const currentObserver = observer.current;
		if (currentTarget) {
			currentObserver.observe(currentTarget);
		}

		return () => {
			if (currentTarget) {
				currentObserver.unobserve(currentTarget);
			}
		};
	}, [observationTarget]);

	return setObservationTarget;
};

export default useInfiniteScroll;
