/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbannotationSwitch.java,v 1.12 2009/03/15 15:08:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.hibernate.hbannotation.*;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbannotation.Fetch;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbAnnotation;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbannotation.Proxy;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage
 * @generated
 */
public class HbannotationSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static HbannotationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public HbannotationSwitch() {
		if (modelPackage == null) {
			modelPackage = HbannotationPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case HbannotationPackage.HB_ANNOTATION: {
				HbAnnotation hbAnnotation = (HbAnnotation)theEObject;
				T result = caseHbAnnotation(hbAnnotation);
				if (result == null) result = casePAnnotation(hbAnnotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.CASCADE: {
				Cascade cascade = (Cascade)theEObject;
				T result = caseCascade(cascade);
				if (result == null) result = caseHbAnnotation(cascade);
				if (result == null) result = casePAnnotation(cascade);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.COLLECTION_OF_ELEMENTS: {
				CollectionOfElements collectionOfElements = (CollectionOfElements)theEObject;
				T result = caseCollectionOfElements(collectionOfElements);
				if (result == null) result = caseHbAnnotation(collectionOfElements);
				if (result == null) result = casePAnnotation(collectionOfElements);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.HB_MAP_KEY: {
				HbMapKey hbMapKey = (HbMapKey)theEObject;
				T result = caseHbMapKey(hbMapKey);
				if (result == null) result = caseHbAnnotation(hbMapKey);
				if (result == null) result = casePAnnotation(hbMapKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.PARAMETER: {
				Parameter parameter = (Parameter)theEObject;
				T result = caseParameter(parameter);
				if (result == null) result = caseHbAnnotation(parameter);
				if (result == null) result = casePAnnotation(parameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.TYPE: {
				Type type = (Type)theEObject;
				T result = caseType(type);
				if (result == null) result = caseHbAnnotation(type);
				if (result == null) result = casePAnnotation(type);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.WHERE: {
				Where where = (Where)theEObject;
				T result = caseWhere(where);
				if (result == null) result = caseHbAnnotation(where);
				if (result == null) result = casePAnnotation(where);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.ID_BAG: {
				IdBag idBag = (IdBag)theEObject;
				T result = caseIdBag(idBag);
				if (result == null) result = caseHbAnnotation(idBag);
				if (result == null) result = casePAnnotation(idBag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.GENERIC_GENERATOR: {
				GenericGenerator genericGenerator = (GenericGenerator)theEObject;
				T result = caseGenericGenerator(genericGenerator);
				if (result == null) result = caseHbAnnotation(genericGenerator);
				if (result == null) result = casePAnnotation(genericGenerator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.CACHE: {
				Cache cache = (Cache)theEObject;
				T result = caseCache(cache);
				if (result == null) result = caseHbAnnotation(cache);
				if (result == null) result = casePAnnotation(cache);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.TYPE_DEF: {
				TypeDef typeDef = (TypeDef)theEObject;
				T result = caseTypeDef(typeDef);
				if (result == null) result = caseHbAnnotation(typeDef);
				if (result == null) result = casePAnnotation(typeDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.FETCH: {
				Fetch fetch = (Fetch)theEObject;
				T result = caseFetch(fetch);
				if (result == null) result = caseHbAnnotation(fetch);
				if (result == null) result = casePAnnotation(fetch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.ON_DELETE: {
				OnDelete onDelete = (OnDelete)theEObject;
				T result = caseOnDelete(onDelete);
				if (result == null) result = caseHbAnnotation(onDelete);
				if (result == null) result = casePAnnotation(onDelete);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.PROXY: {
				Proxy proxy = (Proxy)theEObject;
				T result = caseProxy(proxy);
				if (result == null) result = caseHbAnnotation(proxy);
				if (result == null) result = casePAnnotation(proxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.INDEX: {
				Index index = (Index)theEObject;
				T result = caseIndex(index);
				if (result == null) result = caseHbAnnotation(index);
				if (result == null) result = casePAnnotation(index);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.GENERATED: {
				Generated generated = (Generated)theEObject;
				T result = caseGenerated(generated);
				if (result == null) result = caseHbAnnotation(generated);
				if (result == null) result = casePAnnotation(generated);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.NAMED_QUERY: {
				NamedQuery namedQuery = (NamedQuery)theEObject;
				T result = caseNamedQuery(namedQuery);
				if (result == null) result = caseHbAnnotation(namedQuery);
				if (result == null) result = casePAnnotation(namedQuery);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.FILTER: {
				Filter filter = (Filter)theEObject;
				T result = caseFilter(filter);
				if (result == null) result = caseHbAnnotation(filter);
				if (result == null) result = casePAnnotation(filter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.PARAM_DEF: {
				ParamDef paramDef = (ParamDef)theEObject;
				T result = caseParamDef(paramDef);
				if (result == null) result = caseHbAnnotation(paramDef);
				if (result == null) result = casePAnnotation(paramDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.FILTER_DEF: {
				FilterDef filterDef = (FilterDef)theEObject;
				T result = caseFilterDef(filterDef);
				if (result == null) result = caseHbAnnotation(filterDef);
				if (result == null) result = casePAnnotation(filterDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.DISCRIMINATOR_FORMULA: {
				DiscriminatorFormula discriminatorFormula = (DiscriminatorFormula)theEObject;
				T result = caseDiscriminatorFormula(discriminatorFormula);
				if (result == null) result = caseHbAnnotation(discriminatorFormula);
				if (result == null) result = casePAnnotation(discriminatorFormula);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.NATURAL_ID: {
				NaturalId naturalId = (NaturalId)theEObject;
				T result = caseNaturalId(naturalId);
				if (result == null) result = caseHbAnnotation(naturalId);
				if (result == null) result = casePAnnotation(naturalId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.MAP_KEY_MANY_TO_MANY: {
				MapKeyManyToMany mapKeyManyToMany = (MapKeyManyToMany)theEObject;
				T result = caseMapKeyManyToMany(mapKeyManyToMany);
				if (result == null) result = caseHbAnnotation(mapKeyManyToMany);
				if (result == null) result = casePAnnotation(mapKeyManyToMany);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.FORCE_DISCRIMINATOR: {
				ForceDiscriminator forceDiscriminator = (ForceDiscriminator)theEObject;
				T result = caseForceDiscriminator(forceDiscriminator);
				if (result == null) result = caseHbAnnotation(forceDiscriminator);
				if (result == null) result = casePAnnotation(forceDiscriminator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.IMMUTABLE: {
				Immutable immutable = (Immutable)theEObject;
				T result = caseImmutable(immutable);
				if (result == null) result = caseHbAnnotation(immutable);
				if (result == null) result = casePAnnotation(immutable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.FORMULA: {
				Formula formula = (Formula)theEObject;
				T result = caseFormula(formula);
				if (result == null) result = caseHbAnnotation(formula);
				if (result == null) result = casePAnnotation(formula);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.NOT_FOUND: {
				NotFound notFound = (NotFound)theEObject;
				T result = caseNotFound(notFound);
				if (result == null) result = caseHbAnnotation(notFound);
				if (result == null) result = casePAnnotation(notFound);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.HB_ENTITY: {
				HbEntity hbEntity = (HbEntity)theEObject;
				T result = caseHbEntity(hbEntity);
				if (result == null) result = caseHbAnnotation(hbEntity);
				if (result == null) result = casePAnnotation(hbEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbannotationPackage.BATCH_SIZE: {
				BatchSize batchSize = (BatchSize)theEObject;
				T result = caseBatchSize(batchSize);
				if (result == null) result = caseHbAnnotation(batchSize);
				if (result == null) result = casePAnnotation(batchSize);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotation(HbAnnotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cascade</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cascade</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCascade(Cascade object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Of Elements</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Of Elements</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionOfElements(CollectionOfElements object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Map Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Map Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbMapKey(HbMapKey object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameter(Parameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseType(Type object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Where</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Where</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWhere(Where object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Id Bag</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Id Bag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdBag(IdBag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Generator</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Generator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericGenerator(GenericGenerator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cache</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cache</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCache(Cache object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Def</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDef(TypeDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fetch</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fetch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFetch(Fetch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>On Delete</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>On Delete</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOnDelete(OnDelete object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Proxy</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProxy(Proxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Index</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIndex(Index object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generated</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generated</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenerated(Generated object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Query</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Query</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedQuery(NamedQuery object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilter(Filter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Param Def</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Param Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParamDef(ParamDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Filter Def</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Filter Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFilterDef(FilterDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Discriminator Formula</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Discriminator Formula</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiscriminatorFormula(DiscriminatorFormula object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Natural Id</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Natural Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNaturalId(NaturalId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map Key Many To Many</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map Key Many To Many</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMapKeyManyToMany(MapKeyManyToMany object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Force Discriminator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Force Discriminator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForceDiscriminator(ForceDiscriminator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Immutable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Immutable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImmutable(Immutable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formula</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formula</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormula(Formula object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Not Found</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNotFound(NotFound object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbEntity(HbEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Batch Size</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Batch Size</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBatchSize(BatchSize object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotation(PAnnotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} // HbannotationSwitch
