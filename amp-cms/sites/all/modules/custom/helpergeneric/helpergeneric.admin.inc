<?php

/**
 * @file
 * This file contains form/UI cleanup functions.
 */

/**
 * Page callback for 'admin/settings/ui'.
 */
function helpergeneric_ui_settings_form($form, &$form_state) {
  $cleanup_ui_force = helpergeneric_cleanup_ui();

  $form['cleanup_ui'] = array(
    '#type' => 'fieldset',
    '#title' => t('UI settings'),
    '#description' => '<p>' . t('By default the UI is cleaned, you can disable this behaviour and have access to more options. This is only as a UI cleanup functionality and not a security feature.') . '</p><p>' . t('<strong>DO NOT CLICK</strong> on these buttons unless you were told to!') . '</p>',
    '#collapsible' => FALSE,
    '#collapsed' => FALSE,
  );

  $form['cleanup_ui']['enable'] = array(
    '#type' => 'button',
    '#value' => 'Enable',
    '#disabled' => $cleanup_ui_force,
    '#submit' => array('helpergeneric_toggle_cleanup_ui_force_submit'),
    '#executes_submit_callback' => TRUE,
  );

  $form['cleanup_ui']['disable'] = array(
    '#type' => 'button',
    '#value' => 'Disable',
    '#disabled' => !$cleanup_ui_force,
    '#submit' => array('helpergeneric_toggle_cleanup_ui_force_submit'),
    '#executes_submit_callback' => TRUE,
  );

  return $form;
}

/**
 * Submit handler for helpergeneric_ui_settings_form().
 */
function helpergeneric_toggle_cleanup_ui_force_submit($form, &$form_state) {
  $params = session_get_cookie_params();
  if ($form_state['triggering_element']['#value'] == t('Enable')) {
    // Enable UI cleanup.
    setcookie('helpergeneric_cleanup_ui_force', 1, REQUEST_TIME + (60 * 60 * 12), $params['path'], $params['domain'], $params['secure'], $params['httponly']);
  }
  else {
    // Disable UI cleanup.
    setcookie('helpergeneric_cleanup_ui_force', 0, REQUEST_TIME + (60 * 60 * 12), $params['path'], $params['domain'], $params['secure'], $params['httponly']);
  }
}

/**
 * Page callback for 'admin/settings/api'.
 */
function helpergeneric_api_settings_form($form, &$form_state) {
  $settings = variable_get('helpergeneric_api_settings', array(
    'restclient_hostname' => 'http://localhost:80/rest',
  ));

  $api_status = helpergeneric_check_api_status(FALSE);
  if (!$api_status['online']) {
    drupal_set_message($api_status['message'], 'warning');
  }

  $form['location'] = array(
    '#type' => 'fieldset',
    '#title' => t('Location'),
    '#description' => t('Enter the location of the server.'),
    '#collapsible' => FALSE,
    '#collapsed' => FALSE,
  );

  $example_url = helpergeneric_detect_amp_baseurl() . ':80/rest';
  $form['location']['restclient_hostname'] = array(
    '#type' => 'textfield',
    '#title' => t('Hostname'),
    '#description' => t('The hostname for the server with no trailing slash. Ex: ') . $example_url,
    '#size' => 40,
    '#maxlength' => 255,
    '#default_value' => variable_get('restclient_hostname', 'http://localhost:80/rest'),
  );

  $form['#submit'][] = 'helpergeneric_api_settings_form_submit';

  return system_settings_form($form);
}

/**
 * Submit callback for helpergeneric_api_settings_form().
 */
function helpergeneric_api_settings_form_submit($form, &$form_state) {
  // Clear widget cache.
  $cache_id = 'contentblock:widget:';
  cache_clear_all($cache_id, 'cache', TRUE);
}

/**
 * Page callback for 'admin/settings/generic'.
 */
function helpergeneric_settings_form($form, &$form_state) {
  $form['site_information'] = array(
    '#type' => 'fieldset',
    '#title' => t('Site details'),
  );
  $form['site_information']['site_name'] = array(
    '#type' => 'textfield',
    '#title' => t('Site name'),
    '#description' => t('The site name will be transformed to uppercase before displaying in the header.'),
    '#default_value' => variable_get('site_name', 'Drupal'),
    '#required' => TRUE
  );
  // $form['site_information']['site_slogan'] = array(
  //   '#type' => 'textfield',
  //   '#title' => t('Slogan'),
  //   '#default_value' => variable_get('site_slogan', ''),
  //   '#description' => t("How this is used depends on your site's theme."),
  // );
  $form['site_information']['site_mail'] = array(
    '#type' => 'textfield',
    '#title' => t('E-mail address'),
    '#default_value' => variable_get('site_mail', ini_get('sendmail_from')),
    '#description' => t("The <em>From</em> address in automated e-mails sent during registration and new password requests, and other notifications. (Use an address ending in your site's domain to help prevent this e-mail being flagged as spam.)"),
    '#required' => TRUE,
  );

  $form['#validate'][] = 'helpergeneric_settings_form_validate';

  return system_settings_form($form);
}

/**
 * Validates the submitted site-information form.
 */
function helpergeneric_settings_form_validate($form, &$form_state) {
  // Validate the e-mail address.
  if ($error = user_validate_mail($form_state['values']['site_mail'])) {
    form_set_error('site_mail', $error);
  }
}

/**
 * Page callback for 'admin/settings/language'.
 *
 * @see locale_languages_overview_form()
 */
function helpergeneric_language_settings_form($form, &$form_state) {
  drupal_static_reset('language');
  $languages = language_list();

  $options = array();
  $form['weight'] = array('#tree' => TRUE);
  foreach ($languages as $langcode => $language) {
    $options[$langcode] = '';
    if ($language->enabled) {
      $enabled[] = $langcode;
    }
    $form['weight'][$langcode] = array(
      '#type' => 'weight',
      '#title' => t('Weight for @title', array('@title' => $language->name)),
      '#title_display' => 'invisible',
      '#default_value' => $language->weight,
      '#attributes' => array('class' => array('language-order-weight')),
    );
    $form['name'][$langcode] = array('#markup' => check_plain($language->name));
    $form['native'][$langcode] = array('#markup' => check_plain($language->native));
    $form['direction'][$langcode] = array('#markup' => ($language->direction == LANGUAGE_RTL ? t('Right to left') : t('Left to right')));
  }
  $form['enabled'] = array(
    '#type' => 'checkboxes',
    '#title' => t('Enabled languages'),
    '#title_display' => 'invisible',
    '#options' => $options,
    '#default_value' => $enabled,
  );
  $form['site_default'] = array(
    '#type' => 'radios',
    '#title' => t('Default language'),
    '#title_display' => 'invisible',
    '#options' => $options,
    '#default_value' => language_default('language'),
  );

  $form['form_description'] = array(
    '#markup' => t('Language specific character sets are only loaded when the language is enable, that is why certain characters might have a different font style.'),
    '#prefix' => '<div class="description">',
    '#suffix' => '</div>',
  );

  $form['actions'] = array('#type' => 'actions');
  $form['actions']['submit'] = array('#type' => 'submit', '#value' => t('Save configuration'));
  $form['#theme'] = 'helpergeneric_language_settings_form';

  drupal_set_message('Changing default language might cause existing inbound links to stop working.', 'warning');
  return $form;
}

/**
 * Returns HTML for the language settings form.
 *
 * @see theme_locale_languages_overview_form()
 */
function theme_helpergeneric_language_settings_form($variables) {
  $form = $variables['form'];
  $default = language_default();
  foreach ($form['name'] as $key => $element) {
    // Do not take form control structures.
    if (is_array($element) && element_child($key)) {
      // Disable checkbox for the default language, because it cannot be disabled.
      if ($key == $default->language) {
        $form['enabled'][$key]['#attributes']['disabled'] = 'disabled';
      }

      // Add invisible labels for the checkboxes and radio buttons in the table
      // for accessibility. These changes are only required and valid when the
      // form is themed as a table, so it would be wrong to perform them in the
      // form constructor.
      $title = drupal_render($form['name'][$key]);
      $form['enabled'][$key]['#title'] = t('Enable !title', array('!title' => $title));
      $form['enabled'][$key]['#title_display'] = 'invisible';
      $form['site_default'][$key]['#title'] = t('Set !title as default', array('!title' => $title));
      $form['site_default'][$key]['#title_display'] = 'invisible';
      $rows[] = array(
        'data' => array(
          '<strong>' . $title . '</strong>',
          drupal_render($form['native'][$key]),
          check_plain($key),
          drupal_render($form['direction'][$key]),
          array('data' => drupal_render($form['enabled'][$key]), 'align' => 'center'),
          drupal_render($form['site_default'][$key]),
          drupal_render($form['weight'][$key])
        ),
        'class' => array('draggable'),
      );
    }
  }
  $header = array(
    array('data' => t('English name')),
    array('data' => t('Native name')),
    array('data' => t('Code')),
    array('data' => t('Direction')),
    array('data' => t('Enabled')),
    array('data' => t('Default')),
    array('data' => t('Weight')),
  );
  $output = theme('table', array('header' => $header, 'rows' => $rows, 'attributes' => array('id' => 'language-order')));
  $output .= drupal_render_children($form);

  drupal_add_tabledrag('language-order', 'order', 'sibling', 'language-order-weight');

  return $output;
}


/**
 * Process language overview form submissions, updating existing languages.
 *
 * @see locale_languages_overview_form_submit()
 */
function helpergeneric_language_settings_form_submit($form, &$form_state) {
  $languages = language_list();
  $default = language_default();
  $url_prefixes = variable_get('locale_language_negotiation_url_part', LOCALE_LANGUAGE_NEGOTIATION_URL_PREFIX) == LOCALE_LANGUAGE_NEGOTIATION_URL_PREFIX;

  $enabled_count = 0;
  $disabled_languages = array();
  foreach ($languages as $langcode => $language) {
    if ($form_state['values']['site_default'] == $langcode || $default->language == $langcode) {
      // Automatically enable the default language and the language
      // which was default previously (because we will not get the
      // value from that disabled checkbox).
      $form_state['values']['enabled'][$langcode] = 1;
    }

    // If language URL prefixes are enabled we must clear language domains and
    // assign a valid prefix to each non-default language.
    if ($url_prefixes) {
      $language->domain = '';
      if (empty($language->prefix) && $form_state['values']['site_default'] != $langcode) {
        $language->prefix = $langcode;
      }
    }

    if ($form_state['values']['enabled'][$langcode]) {
      $enabled_count++;
      $language->enabled = 1;
    }
    else {
      $language->enabled = 0;
      $disabled_languages[] = $langcode;
    }

    $language->weight = $form_state['values']['weight'][$langcode];

    db_update('languages')
      ->fields(array(
        'enabled' => $language->enabled,
        'weight' => $language->weight,
        'prefix' => $language->prefix,
        'domain' => $language->domain,
      ))
      ->condition('language', $langcode)
      ->execute();

    $languages[$langcode] = $language;
  }

  // Set disabled languages. Overrides language settings from ampi18n feature.
  // @see helpergeneric_locale_default_languages_alter()
  variable_set('helpergeneric_disabled_languages', $disabled_languages);

  variable_set('language_default', $languages[$form_state['values']['site_default']]);
  variable_set('language_count', $enabled_count);
  drupal_set_message(t('Configuration saved.'));

  // Changing the language settings impacts the interface.
  cache_clear_all('*', 'cache_page', TRUE);
  module_invoke_all('multilingual_settings_changed');

  return;
}