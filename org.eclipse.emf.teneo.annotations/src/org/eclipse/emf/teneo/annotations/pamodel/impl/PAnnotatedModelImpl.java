/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedModelImpl.java,v 1.26 2008/10/13 05:35:43 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PAnnotated Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedModelImpl#getPaEPackages <em>Pa EPackages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PAnnotatedModelImpl extends EObjectImpl implements PAnnotatedModel {

	private static final Log log = LogFactory.getLog(PAnnotatedModelImpl.class);

	@SuppressWarnings("unchecked")
	class MapTrackingAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification msg) {
			// TODO handle resolve?
			Object msgFeature = msg.getFeature();
			if (!msg.isTouch() && msgFeature == PamodelPackage.eINSTANCE.getPAnnotatedEModelElement_ModelElement()) {
				switch (msg.getEventType()) {
					case Notification.SET:
					case Notification.UNSET:
						removeMapping((EModelElement) msg.getOldValue());
						addMapping((PAnnotatedEModelElement) msg.getNotifier());
						break;
				}
			} else if (msgFeature instanceof EReference && !msg.isTouch()) {
				EReference r = (EReference) msgFeature;
				if (PamodelPackage.eINSTANCE.getPAnnotatedEModelElement().isSuperTypeOf(r.getEReferenceType()) &&
						r.isContainment()) {
					switch (msg.getEventType()) {
						case Notification.UNSET:
						case Notification.SET:
							if (msg.getOldValue() != null) {
								detach((PAnnotatedEModelElement) msg.getOldValue());
							}
							if (msg.getNewValue() != null) {
								attach((PAnnotatedEModelElement) msg.getNewValue());
							}
							break;
						case Notification.ADD:
							if (msg.getNewValue() != null) {
								attach((PAnnotatedEModelElement) msg.getNewValue());
							}
							break;
						case Notification.ADD_MANY:
							attach((List<? extends Object>) msg.getNewValue());
							break;
						case Notification.REMOVE:
							if (msg.getOldValue() != null) {
								detach((PAnnotatedEModelElement) msg.getOldValue());
							}
							break;
						case Notification.REMOVE_MANY:
							detach((List<? extends Object>) msg.getNewValue());
							break;
					}
				}
			}
			super.notifyChanged(msg);
		}
	};

	final MapTrackingAdapter itsMapTrackingAdapter;

	/**
	 * The cached value of the '{@link #getPaEPackages() <em>Pa EPackages</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPaEPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<PAnnotatedEPackage> paEPackages;

	/**
	 * Record the association between the EModelElement and the PAnnotatedEModelElement that are
	 * contained in this PAnnotatedModel content.
	 */
	protected final Map<EModelElement, PAnnotatedEModelElement> eElement_to_pElement;

	/** Set to true if initialized */
	private boolean initialized = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected PAnnotatedModelImpl() {
		eElement_to_pElement = new HashMap<EModelElement, PAnnotatedEModelElement>();
		itsMapTrackingAdapter = new MapTrackingAdapter();
		this.eAdapters().add(itsMapTrackingAdapter);
	}

	/** Return the mapping from epackage elements to annotated package elements */
	public Map<EModelElement, PAnnotatedEModelElement> getModelElementMapping() {
		return eElement_to_pElement;
	}

	/** Merge the passed annotated model in this one */
	public void merge(PAnnotatedModel pModel) {
		eElement_to_pElement.putAll(pModel.getModelElementMapping());
	}

	/**
	 * Register the association among a PAnnotatedEModelElement and its annotated EModelElement. If
	 * another PAnnotatedEModelElement was associatied to the same EModelElement, such association
	 * will be broken.
	 * 
	 * <p>
	 * In order to mantain the consistency of the model, whenever an association among a
	 * PAnnotatedEModelElement <code>pa</code> and an EModelElement <code>e</code> is broken, the
	 * annotatedElement feature of <code>pa</code> is unset.
	 */
	protected void addMapping(PAnnotatedEModelElement target) {
		final EModelElement eModelElement = target.getModelElement();
		if (eModelElement != null) {
			PAnnotatedEModelElement prevAssoc = eElement_to_pElement.get(eModelElement);
			if (prevAssoc != null) {
				removeMapping(eModelElement);
				prevAssoc.setModelElement(null);
			}
			eElement_to_pElement.put(eModelElement, target);
		}
	}

	/**
	 * Unregister the association between an <code>EModelElement</code> and the
	 * <code>PAnnotatedEModelElement</code> that references it via the <code>annotatedElement</code>
	 * feature.
	 */
	protected void removeMapping(EModelElement eModelElement) {
		final Object prevAssoc = eElement_to_pElement.remove(eModelElement);
		assert prevAssoc != null;
	}

	/**
	 * Invoked whenever a new PAnnotatedEModelElement is added to the PAnnotatedModel.
	 */
	protected void attach(PAnnotatedEModelElement aElement) {
		aElement.eAdapters().add(0, itsMapTrackingAdapter);
		addMapping(aElement);
		attach(aElement.eContents());
	}

	/**
	 * Invoked whenever a set of new PAnnotatedEModelElement is added to the PAnnotatedModel.
	 */
	protected void attach(List<? extends Object> aElements) {
		for (Object x : aElements) {
			if (x instanceof PAnnotatedEModelElement) {
				attach((PAnnotatedEModelElement) x);
			}
		}
	}

	/**
	 * Invoked whenever a PAnnotatedEModelElement is removed from the PAnnotatedModel.
	 */
	protected void detach(PAnnotatedEModelElement aElement) {
		aElement.eAdapters().remove(itsMapTrackingAdapter);
		ENamedElement annotatedElement = aElement.getModelElement();
		if (annotatedElement != null) {
			removeMapping(annotatedElement);
		}
		detach(aElement.eContents());
	}

	/**
	 * Invoked whenever a set of PAnnotatedEModelElement is removed from the PAnnotatedModel.
	 */
	protected void detach(List<? extends Object> aElements) {
		for (Object x : aElements) {
			if (x instanceof PAnnotatedEModelElement) {
				detach((PAnnotatedEModelElement) x);
			}
		}
	}

	/**
	 * Invariant: the eElement_to_pElement map agree with the PAnnotatedModel content.
	 * <p>
	 * This method should be used only for debugging. TODO use in validation.
	 */
	public void invMapIsWellFormed() {
		Set<EModelElement> definedUnused = new HashSet<EModelElement>(eElement_to_pElement.keySet());
		for (TreeIterator<? extends Object> i = EcoreUtil.getAllContents(eContents()); i.hasNext();) {
			Object x = i.next();
			if (x instanceof PAnnotatedEModelElement) {
				PAnnotatedEModelElement paElement = (PAnnotatedEModelElement) x;
				ENamedElement annotatedElement = paElement.getModelElement();
				if (annotatedElement != null) {
					assert getPAnnotated(annotatedElement) == paElement;
					definedUnused.remove(annotatedElement);
				}
			} else {
				i.prune();
			}
		}
		assert definedUnused.isEmpty();
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEModelElement</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EModelElement</code>.
	 */
	public PAnnotatedEModelElement getPAnnotated(EModelElement e) {
		checkAnnotatedPresent(e, eElement_to_pElement);
		return eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEPackage</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EPackage</code>.
	 */
	public PAnnotatedEPackage getPAnnotated(EPackage e) {
		checkAnnotatedPresent(e, eElement_to_pElement);
		return (PAnnotatedEPackage) eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEClass</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EClass</code>.
	 */
	public PAnnotatedEClass getPAnnotated(EClass e) {
		// in this case do not throw an error
		if (e.getEPackage() instanceof EcorePackage) {
			return (PAnnotatedEClass) eElement_to_pElement.get(e);
		}
		checkAnnotatedPresent(e, eElement_to_pElement);
		return (PAnnotatedEClass) eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEStructuralFeature</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EStructuralFeature</code>.
	 */
	public PAnnotatedEStructuralFeature getPAnnotated(EStructuralFeature e) {
		checkAnnotatedPresent(e, eElement_to_pElement);
		return (PAnnotatedEStructuralFeature) eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEDataType</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EDataType</code>.
	 */
	public PAnnotatedEDataType getPAnnotated(EDataType e) {
		// do not check because many datatypes belong to the emf package
		return (PAnnotatedEDataType) eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEAttribute</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EAttribute</code>.
	 */
	public PAnnotatedEAttribute getPAnnotated(EAttribute e) {
		checkAnnotatedPresent(e, eElement_to_pElement);
		return (PAnnotatedEAttribute) eElement_to_pElement.get(e);
	}

	/**
	 * @return Returns the unique <code>PAnnotatedEReference</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EReference</code>.
	 */
	public PAnnotatedEReference getPAnnotated(EReference e) {
		checkAnnotatedPresent(e, eElement_to_pElement);
		return (PAnnotatedEReference) eElement_to_pElement.get(e);
	}

	/**
	 * Checks if there is a annotated model element for the passed emf model element. If not then an
	 * illegal argument is thrown
	 */
	private void checkAnnotatedPresent(EModelElement ee, Map<EModelElement, PAnnotatedEModelElement> map) {
		if (!isInitialized()) {
			return; // at this point the model is not yet fully initialized.
		}
		if (ee == null) {
			throw new IllegalArgumentException(
				"Trying to retrieve Annotated Type using null. "
						+ "This can occur if not all epackages have been registered with Teneo.\n"
						+ "Or this can occur if epackages which refer to eachother are placed in different ecore/xsd files "
						+ "and they are not read using one resource set. The reference from one epackage to another must be "
						+ "resolvable by EMF.");
		}
		if (map.get(ee) == null) {
			final String name = ee instanceof ENamedElement ? " for: " + ((ENamedElement) ee).getName() : "";
			throw new IllegalArgumentException("No annotated model element present " + name + " for type " +
					ee.eClass().getName() + " has its epackage been registered with Teneo?");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PamodelPackage.Literals.PANNOTATED_MODEL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PAnnotatedEPackage> getPaEPackages() {
		if (paEPackages == null) {
			paEPackages = new EObjectContainmentWithInverseEList<PAnnotatedEPackage>(PAnnotatedEPackage.class, this, PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES, PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL);
		}
		return paEPackages;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPaEPackages()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				return ((InternalEList<?>)getPaEPackages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				return getPaEPackages();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				getPaEPackages().clear();
				getPaEPackages().addAll((Collection<? extends PAnnotatedEPackage>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				getPaEPackages().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES:
				return paEPackages != null && !paEPackages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Returns a sequence generator on the basis of its name, if not found then an exception is
	 * thrown. efeature is passed for debugging purposes.
	 */
	public SequenceGenerator getSequenceGenerator(EStructuralFeature efeature, String name) {
		// TODO: should not only the paepackage of the efeature be checked?
		for (PAnnotatedEPackage pae : getPaEPackages()) {
			for (SequenceGenerator sg : pae.getSequenceGenerators()) {
				if (sg.getName() != null && sg.getName().compareTo(name) == 0) {
					return sg;
				}
			}
		}
		throw new IllegalStateException("No sequence generator found with the name: " + name + ", name is used in " +
				"annotation of element " + efeature.getEContainingClass().getName() + "/" + efeature.getName());
	}

	public SequenceStyleGenerator getSequenceStyleGenerator(EStructuralFeature efeature, String name) {
		// TODO: should not only the paepackage of the efeature be checked?
		for (PAnnotatedEPackage pae : getPaEPackages()) {
			for (SequenceStyleGenerator sg : pae.getSequenceStyleGenerators()) {
				if (sg.getName() != null && sg.getName().compareTo(name) == 0) {
					return sg;
				}
			}
		}
		throw new IllegalStateException("No sequence generator found with the name: " + name + ", name is used in " +
				"annotation of element " + efeature.getEContainingClass().getName() + "/" + efeature.getName());
	}

	/**
	 * Returns a table generator on the basis of its name, if not found then an exception is thrown.
	 * efeature is passed for debugging purposes.
	 */
	public TableGenerator getTableGenerator(EStructuralFeature efeature, String name) {
		// TODO: should not only the paepackage of the efeature be checked?
		for (PAnnotatedEPackage pae : getPaEPackages()) {
			for (TableGenerator tg : pae.getTableGenerators()) {
				if (tg.getName() != null && tg.getName().compareTo(name) == 0) {
					return tg;
				}
			}
			for (PAnnotatedEClass pec : pae.getPaEClasses()) {
				if (pec.getTableGenerator() != null && pec.getTableGenerator().getName() != null &&
						pec.getTableGenerator().getName().compareTo(name) == 0) {
					return pec.getTableGenerator();
				}

				for (PAnnotatedEStructuralFeature pef : pec.getPaEStructuralFeatures()) {
					for (TableGenerator tg : pef.getTableGenerators()) {
						if (tg.getName() != null && tg.getName().compareTo(name) == 0) {
							return tg;
						}
					}
				}
			}
		}

		log.debug("No table generator found with the name: " + name + ", name is used in " + "annotation of element " +
				efeature.getEContainingClass().getName() + "/" + efeature.getName());
		return null;
	}

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized
	 *            the initialized to set
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	private ConcurrentHashMap<String, EClass> entityNameToEClass = new ConcurrentHashMap<String, EClass>();

	/** @return the eclass annotated with this entity name. If not found then an exception is thrown */
	public EClass getEClass(String entityName) {
		EClass eClass;
		if ((eClass = entityNameToEClass.get(entityName)) != null) {
			return eClass;
		}
		for (PAnnotatedEPackage aPackage : getPaEPackages()) {
			for (PAnnotatedEClass aClass : aPackage.getPaEClasses()) {
				if (aClass.getEntity() != null && aClass.getEntity().getName() != null &&
						aClass.getEntity().getName().compareTo(entityName) == 0) {
					entityNameToEClass.put(entityName, aClass.getModelEClass());
					return aClass.getModelEClass();
				}
			}
		}
		throw new IllegalArgumentException("No Annotated EClass for entityName " + entityName);
	}

	/**
	 * @return the EClassifier for a certain name. First the eclasses are searched using the name as
	 *         the entityname then the edatatypes are searched.
	 */
	public EClassifier getEClassifier(String name) {
		if (hasEClass(name)) {
			return getEClass(name);
		}

		for (PAnnotatedEPackage aPackage : getPaEPackages()) {
			final EClassifier eClassifier = aPackage.getModelEPackage().getEClassifier(name);
			if (eClassifier instanceof EDataType) {
				return eClassifier;
			}
		}
		throw new IllegalArgumentException("No EClassifier for name " + name);
	}

	/**
	 * @return true if there is an EClass with the name as entityname or an EDataType with the name
	 *         passed as a parameter.
	 */
	public boolean hasEClassifier(String name) {
		if (hasEClass(name)) {
			return true;
		}

		for (PAnnotatedEPackage aPackage : getPaEPackages()) {
			final EClassifier eClassifier = aPackage.getModelEPackage().getEClassifier(name);
			if (eClassifier instanceof EDataType) {
				return true;
			}
		}
		return false;
	}

	/** @return true if there is annotated eclass with the passed entityname */
	public boolean hasEClass(String entityName) {
		if (entityNameToEClass.get(entityName) != null) {
			return true;
		}
		for (PAnnotatedEPackage aPackage : getPaEPackages()) {
			for (PAnnotatedEClass aClass : aPackage.getPaEClasses()) {
				if (aClass.getEntity() != null && aClass.getEntity().getName() != null &&
						aClass.getEntity().getName().compareTo(entityName) == 0) {
					entityNameToEClass.put(entityName, aClass.getModelEClass());
					return true;
				}
			}
		}
		return false;
	}

	/** Dump this model to xml */
	public String toXML() {
		try {
			final XMLResourceImpl xmlResource = new XMLResourceImpl();
			xmlResource.getContents().add(this);
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final HashMap<String, String> options = new HashMap<String, String>();
			options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_RECORD);
			xmlResource.save(bos, options);
			final String res = bos.toString();
			bos.close();
			return res;
		} catch (IOException e) {
			// todo replace with teneo exception
			throw new IllegalStateException("Exception while converting pamodel to a xml string", e);
		}
	}

} // PAnnotatedModelImpl
