import React, { useState } from 'react';
import { BoxCategoryContainer } from './style';

const BoxCategoryW = () => {
	const [currentTab, setCurrentTab] = useState(0);
	const category = [
		{ id: 0, title: '면접 질문', content: '면접질문W' },
		{ id: 1, title: '스터디 모집', content: '스터디 모집W' },
		{ id: 2, title: '면접 후기', content: '면접 후기W' },
	];
	const selectMenuHandler = (index: number) => {
		setCurrentTab(index);
	};
	const currentpage = category[currentTab].content;
	return (
		<>
			<BoxCategoryContainer>
				{category.map((ele, index) => (
					<div
						key={index}
						className={currentTab === index ? 'submenu focused' : 'submenu'}
						onClick={() => selectMenuHandler(index)}
					>
						{ele.title}
					</div>
				))}
			</BoxCategoryContainer>
			<div>{currentpage}</div>
		</>
	);
};

export default BoxCategoryW;
