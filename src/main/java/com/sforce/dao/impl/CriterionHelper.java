package com.sforce.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.sforce.domain.support.AndCondition;
import com.sforce.domain.support.BetweenCondition;
import com.sforce.domain.support.Condition;
import com.sforce.domain.support.InCondition;
import com.sforce.domain.support.LikeCondition;
import com.sforce.domain.support.NotNullCondition;
import com.sforce.domain.support.NullCondition;
import com.sforce.domain.support.OrCondition;
import com.sforce.domain.support.SimpleCondition;

public abstract class CriterionHelper {
	public static Criterion parse(Condition condition) {
		Criterion result = null;
		switch (condition.getConditionEnum()) {
		case And:
			result = parse((AndCondition) condition);
			break;
		case Between:
			result = parse((BetweenCondition) condition);
			break;
		case In:
			result = parse((InCondition) condition);
			break;
		case NotNull:
			result = parse((NotNullCondition) condition);
			break;
		case Null:
			result = parse((NullCondition) condition);
			break;
		case Or:
			result = parse((OrCondition) condition);
			break;
		case Like:
			result = parse((LikeCondition) condition);
			break;
		case Simple:
			result = parse((SimpleCondition) condition);
			break;
		}
		return result;
	}

	public static Criterion parse(SimpleCondition sc) {
		Criterion result = null;
		switch (sc.getOperation()) {
		case EQ:
			if (sc.isIgnoreCase()) {
				result = Restrictions.eq(sc.getFieldName(), sc.getValue()).ignoreCase();
			} else {
				result = Restrictions.eq(sc.getFieldName(), sc.getValue());
			}
			break;
		case NE:
			result = Restrictions.ne(sc.getFieldName(), sc.getValue());
			break;
		case GE:
			result = Restrictions.ge(sc.getFieldName(), sc.getValue());
			break;
		case GT:
			result = Restrictions.gt(sc.getFieldName(), sc.getValue());
			break;
		case LE:
			result = Restrictions.le(sc.getFieldName(), sc.getValue());
			break;
		case LT:
			result = Restrictions.lt(sc.getFieldName(), sc.getValue());
			break;
		}

		return result;
	}

	public static Criterion parse(BetweenCondition bc) {
		Criterion result = Restrictions.between(bc.getFieldName(), bc.getMinValue(), bc.getMaxValue());
		return result;
	}

	public static Criterion parse(InCondition ic) {
		Criterion result = Restrictions.in(ic.getFieldName(), ic.getValues());
		return result;
	}

	public static Criterion parse(NullCondition nc) {
		Criterion result = Restrictions.isNull(nc.getFieldName());
		return result;
	}

	public static Criterion parse(NotNullCondition nnc) {
		Criterion result = Restrictions.isNotNull(nnc.getFieldName());
		return result;
	}

	public static Criterion parse(OrCondition oc) {
		List<Criterion> crits = new ArrayList<Criterion>();
		for (Condition cond : oc.getConditions()) {
			crits.add(parse(cond));
		}
		Criterion result = Restrictions.or(crits.toArray(new Criterion[crits.size()]));
		return result;
	}

	public static Criterion parse(AndCondition ac) {
		Criterion cri1 = parse(ac.getCondition1());
		Criterion cri2 = parse(ac.getCondition2());
		Criterion result = Restrictions.and(cri1, cri2);
		return result;
	}
	
	public static Criterion parse(LikeCondition lc) {
		Criterion result = null;
		if (lc.isIgnoreCase()) {
			result = Restrictions.like(lc.getFieldName(), lc.getValue()).ignoreCase();
		} else {
			result = Restrictions.like(lc.getFieldName(), lc.getValue());
		}
		return result;
	}
}
