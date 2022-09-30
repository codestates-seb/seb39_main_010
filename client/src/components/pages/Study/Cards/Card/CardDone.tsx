import React from 'react';
import { BsFillPersonFill, BsChatLeftDots, BsSuitHeart } from 'react-icons/bs';
import { CardContainer, Top, Middle, Bottom } from './style';
import dummy from '../../../../../assets/data/dummy.json';

const CardDone = () => {
	const sortedCard = dummy.study
		.slice(0)
		.filter((item) => item.state === '모집완료');
	return (
		<>
			{sortedCard.map((item) => (
				<CardContainer key={item.id}>
					<div className="box">
						<Top>
							<div>{item.state}</div>
							<div>{item.created_at}</div>
						</Top>
						<Middle>
							<div>
								<h3>{item.title}</h3>
								<div>{item.tag}</div>
							</div>
							<p>{item.content}</p>
						</Middle>
						<Bottom>
							<div>
								<div>
									<BsFillPersonFill size={20} />
								</div>
								<span>{item.nickname}</span>
							</div>
							<div>
								<div className="likes">
									<div className="hearticon">
										<BsSuitHeart size={20} />
									</div>
									<span>{item.likes}</span>
								</div>
								<div className="comments">
									<div className="chaticon">
										<BsChatLeftDots size={20} />
									</div>
									<span>{item.comments}</span>
								</div>
							</div>
						</Bottom>
					</div>
				</CardContainer>
			))}
		</>
	);
};
export default CardDone;
