import React from 'react';
import BoxCategoryW from './BoxCategoryW';
import { SortingBoxContainer } from './style';

const SortingBoxW = () => {
	return (
		<SortingBoxContainer>
			<BoxCategoryW />
			<div>내가 작성한 글</div>
		</SortingBoxContainer>
	);
};

export default SortingBoxW;
